package com.mydiploma.autohelper.ui.car;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
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

import java.time.LocalDate;

public class AddCarActivity extends AppCompatActivity {
    boolean success;
    @RequiresApi(api = Build.VERSION_CODES.O)
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
            car.setMaker (((EditText) findViewById(R.id.input_maker)).getText().toString());
            car.setModel (((EditText) findViewById(R.id.input_model)).getText().toString());
            car.setEngineVolume (Float.parseFloat(((EditText) findViewById(R.id.input_engine_volume)).getText().toString()));
            car.setTransmission (((EditText) findViewById(R.id.input_type_of_transmission)).getText().toString());
            car.setColor (((EditText) findViewById(R.id.input_color)).getText().toString());
            car.setProductionYear (Integer.parseInt(((EditText) findViewById(R.id.input_production_year)).getText().toString()));
            car.setCurrentOilBrand (((EditText) findViewById(R.id.input_current_oil_brand)).getText().toString());
            //LocalDate date = LocalDate.parse(findViewById(R.id.input_insurance_run_out_date));
            //car.setInsuranceRunOutDate(date);
            car.setInsuranceRunOutDate (((EditText) findViewById(R.id.input_insurance_run_out_date)).getText().toString());
            success = CarUtil.addNewCar(car, carDatabase);
            if(success) {
                this.finish();
            }
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

}