package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.util.CarUtil;

public class AddCarActivity extends AppCompatActivity {
    boolean success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        Button ok = findViewById(R.id.save_car_button);
        Button cancelAdd = findViewById(R.id.cancel_add_car_button);
        // save new car
        ok.setOnClickListener(v -> {
            // entrance into carDatabase
            CarDatabase carDatabase =  Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR).build();
            // rebase db code
/*            CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR)
                    .fallbackToDestructiveMigration()
                    .build();*/
            Car car = new Car();
            // take values from fields
            String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
            Spinner spinner = findViewById(R.id.spinner);
            // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
            // Определяем разметку для использования при выборе элемента
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Применяем адаптер к элементу spinner
            spinner.setAdapter(adapter);
            //Spinner spinner = findViewById(R.id.spinner);
            //spinner.setText
            car.setMaker (((EditText) findViewById(R.id.input_maker)).getText().toString());
            car.setModel (((EditText) findViewById(R.id.input_model)).getText().toString());
            car.setEngineVolume (Float.parseFloat(((EditText) findViewById(R.id.input_engine_volume)).getText().toString()));
            car.setTransmission (((EditText) findViewById(R.id.input_type_of_transmission)).getText().toString());
            car.setColor (((EditText) findViewById(R.id.input_color)).getText().toString());
            car.setProductionYear (Integer.parseInt(((EditText) findViewById(R.id.input_production_year)).getText().toString()));
            car.setCurrentOilBrand (((EditText) findViewById(R.id.input_current_oil_brand)).getText().toString());
            car.setInsuranceRunOutDate (((EditText) findViewById(R.id.input_insurance_run_out_date)).getText().toString());
            success = CarUtil.addNewCar(car, carDatabase);
            if(success) {
                this.finish();
            }
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

}