package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.R;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        Button ok = findViewById(R.id.saveCarButton);
        Button cancelAdd = findViewById(R.id.cancelAddCarButton);
        ok.setOnClickListener(v -> {


/*            CarDatabase db = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR)
                    .fallbackToDestructiveMigration()
                    .build();*/


            CarDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR).build();


            Car car = new Car();
            car.setMaker (((EditText) findViewById(R.id.input_maker)).getText().toString());
            car.setModel (((EditText) findViewById(R.id.input_model)).getText().toString());
            car.setEngineVolume (Float.parseFloat(((EditText) findViewById(R.id.input_engine_volume)).getText().toString()));
            car.setTransmission (((EditText) findViewById(R.id.input_type_of_transmission)).getText().toString());
            car.setColor (((EditText) findViewById(R.id.input_color)).getText().toString());
            car.setProductionYear (Integer.parseInt(((EditText) findViewById(R.id.input_production_year)).getText().toString()));
            car.setCurrentOilBrand (((EditText) findViewById(R.id.input_current_oil_brand)).getText().toString());
            car.setInsuranceRunOutDate (((EditText) findViewById(R.id.input_insurance_run_out_date)).getText().toString());
            CarDao carDao = db.carDao();
            Thread thread = new Thread(){
                @Override
                public void run() {
                    carDao.insert(car);
                }
            };
            thread.start();
            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }
}