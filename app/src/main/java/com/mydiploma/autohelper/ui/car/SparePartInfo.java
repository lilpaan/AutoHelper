package com.mydiploma.autohelper.ui.car;

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
import com.mydiploma.autohelper.util.SparePartUtil;

public class SparePartInfo extends AppCompatActivity {
    CarDatabase carDatabase;
    SparePartDao sparePartDao;
    SparePart sparePart;
    boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_part_info);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        Button finishSparePart;

        // open DB
        carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                Constants.CAR).build();

        // show info func
        showSparePartInfo(id);

        // finishSparePart for finish activity
        finishSparePart = findViewById(R.id.finish_spare_part);
        finishSparePart.setOnClickListener(v -> finish());

        // button for delete sparePart
        Button delete = findViewById(R.id.delete_spare_part);

        delete.setOnClickListener(v -> {
            success = SparePartUtil.deleteSparePart(carDatabase, id);
            if(success) {
                finish();
            }
        });
    }

    public void showSparePartInfo(Long id) {
        // thread to get sparePart info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
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
    }

}