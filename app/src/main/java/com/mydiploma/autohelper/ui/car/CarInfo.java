package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

public class CarInfo extends AppCompatActivity {
    CarDatabase carDatabase;
    CarDao carDao;
    SparePartDao sparePartDao;
    Car car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        // thread to get car info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                        Constants.CAR).build();
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
        viewForInsuranceRunOutDate.setText(car.getInsuranceRunOutDate());
        viewForCurrentOilBrand.setText(car.getCurrentOilBrand());
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
        delete.setOnClickListener(v -> {
            // thread to delete car
            Thread deleteCarThread = new Thread(){
                @Override
                public void run() {
                    carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                            Constants.CAR).build();
                    carDao = carDatabase.carDao();
                    carDao.delete(car);
                }
            };
            deleteCarThread.start();
            try {
                deleteCarThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread deleteSparePartThread = new Thread(){
                @Override
                public void run() {
                    carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                            Constants.CAR).build();
                    sparePartDao = carDatabase.sparePartDao();
                    sparePartDao.deleteSparePart(id);
                }
            };
            deleteSparePartThread.start();
            try {
                deleteSparePartThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        });
    }
}