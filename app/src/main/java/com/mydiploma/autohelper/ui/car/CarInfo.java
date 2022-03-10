package com.mydiploma.autohelper.ui.car;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.util.CarUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarInfo extends AppCompatActivity {
    CarDatabase carDatabase;
    CarDao carDao;
    Car car;
    boolean success;
    @SuppressLint("SimpleDateFormat") SimpleDateFormat DateFor
            = new SimpleDateFormat(Constants.DATE_PATTERN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        Long id = getIntent().getLongExtra(Constants.ID, 0);

        // open DB
        carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                Constants.CAR).build();

        // show car info func
        showCarInfo(carDatabase, id);

        // spare part button activity
        Button sparePartButton = findViewById(R.id.spare_part_button);
        sparePartButton.setOnClickListener(v -> {
            Intent intentToSparePart = new Intent(CarInfo.this, SparePartActivity.class);
            intentToSparePart.putExtra(Constants.ID, id);
            startActivity(intentToSparePart);
        });

        // finishCarButton for finish activity
        Button finishCarButton = findViewById(R.id.finish_car);
        finishCarButton.setOnClickListener(v -> finish());

        // button for delete car
        Button delete = findViewById(R.id.delete_car);

        // thread to delete car
        delete.setOnClickListener(v -> {
            carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                    Constants.CAR).build();
            success = CarUtil.deleteCar(carDatabase, car);
            if (success) {
                finish();
            }
        });
    }

    public void showCarInfo(CarDatabase carDatabase, Long id) {
        // thread to get car info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
                carDao = carDatabase.carDao();
                car = carDao.getById(id);
            }
        };
        threadToGetCarInfo.start();
        try {
            threadToGetCarInfo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // text views for output info
        TextView viewForModel = findViewById(R.id.view_for_model);
        TextView viewForMaker = findViewById(R.id.view_for_maker);
        TextView viewForEngineVolume = findViewById(R.id.view_for_engine_volume);
        TextView viewForColor = findViewById(R.id.view_for_color);
        TextView viewForTransmission = findViewById(R.id.view_for_transmission);
        TextView viewForProductionYear = findViewById(R.id.view_for_production_year);
        TextView viewForInsuranceRunOutDate = findViewById(R.id.view_for_insurance_run_out_date);
        TextView viewForCurrentOilBrand = findViewById(R.id.view_for_current_oil_brand);

        // place info to text views
        viewForModel.setText(car.getModel());
        viewForMaker.setText(car.getMaker());
        viewForEngineVolume.setText(String.valueOf(car.getEngineVolume()));
        viewForColor.setText(car.getColor());
        viewForTransmission.setText(car.getTransmission());
        viewForProductionYear.setText(String.valueOf(car.getProductionYear()));
        viewForInsuranceRunOutDate.setText(DateFor.format(car.getInsuranceRunOutDate()));
        viewForCurrentOilBrand.setText(car.getCurrentOilBrand());
    }

}