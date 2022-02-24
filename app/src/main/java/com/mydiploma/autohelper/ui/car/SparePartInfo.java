package com.mydiploma.autohelper.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.SparePart;

public class SparePartInfo extends AppCompatActivity {
    CarDatabase carDatabase;
    SparePartDao sparePartDao;
    SparePart sparePart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_part_info);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        // thread to get sparePart info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                        Constants.CAR).build();
                sparePartDao = carDatabase.sparePartDao();
                sparePart = sparePartDao.getById(id);
            }
        };
        threadToGetCarInfo.start();
        try {
            threadToGetCarInfo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // text views for output info
        TextView viewForSparePartType = findViewById(R.id.view_for_spare_part_type);
        TextView viewForSparePartMaker = findViewById(R.id.view_for_spare_part_maker);
        TextView viewForSparePartInstallationDate =
                findViewById(R.id.view_for_spare_part_installation_date);
        // place info to text views
        viewForSparePartType.setText(sparePart.getType());
        viewForSparePartMaker.setText(sparePart.getMaker());
        viewForSparePartInstallationDate.setText(String.valueOf(sparePart.getInstallationDate()));
        // finishSparePart for finish activity
        Button finishSparePart = findViewById(R.id.finish_spare_part);
        finishSparePart.setOnClickListener(v -> finish());
        // button for delete sparePart
        Button delete = findViewById(R.id.delete_spare_part);
        delete.setOnClickListener(v -> {
            // thread to delete sparePart
            Thread deleteCarThread = new Thread(){
                @Override
                public void run() {
                    carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                            Constants.CAR).build();
                    sparePartDao = carDatabase.sparePartDao();
                    sparePartDao.delete(sparePart);
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