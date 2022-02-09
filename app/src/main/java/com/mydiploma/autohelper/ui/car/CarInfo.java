package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

public class CarInfo extends AppCompatActivity {
    CarDatabase carDatabase;
    CarDao carDao;
    Car car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        long id = getIntent().getLongExtra("ID", 0);
        Thread thread = new Thread(){
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                        Constants.CAR).build();
                carDao = carDatabase.carDao();
                car = carDao.getById(id);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextView textView = findViewById(R.id.model);
        textView.setText(car.getModel());
        TextView textViewmarka = findViewById(R.id.marka);
        textViewmarka.setText(car.getMaker());
        Button button = findViewById(R.id.finish);
        button.setOnClickListener(v -> finish());
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            Thread deleteThread = new Thread(){
                @Override
                public void run() {
                    carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                            Constants.CAR).build();
                    carDao = carDatabase.carDao();
                    carDao.delete(car);
                }
            };
            deleteThread.start();
            try {
                deleteThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        });
    }
}