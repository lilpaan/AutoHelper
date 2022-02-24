package com.mydiploma.autohelper.ui.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.adapter.SparePartAdapter;
import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.SparePart;
import com.mydiploma.autohelper.ui.card.BusinessCardInfo;

public class SparePartActivity extends AppCompatActivity {
    CarDatabase carDatabase;
    SparePartDao sparePartDao;
    SparePart[] spareParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spare_part_activity);
        long spareId = getIntent().getLongExtra(Constants.ID, 0);
        Button addSparePart = findViewById(R.id.add_spare_part_button);
        ListView sparePartListView = findViewById(R.id.added_spare_part_list);
        // add spare part
        addSparePart.setOnClickListener(v -> {
            Intent intentToAddSparePart
                    = new Intent(SparePartActivity.this, AddSparePartActivity.class);
            intentToAddSparePart.putExtra(Constants.ID, spareId);
            startActivity(intentToAddSparePart);
        });
        // thread to show spare part items
        Thread threadToShowSparePart = new Thread(){
            @Override
            public void run() {
/*                carDatabase = Room.databaseBuilder(getApplicationContext(),
                        CarDatabase.class, Constants.CAR)
                        .fallbackToDestructiveMigration()
                        .build();*/
                carDatabase = Room.databaseBuilder(SparePartActivity.this, CarDatabase.class,
                        Constants.CAR).build();
                sparePartDao = carDatabase.sparePartDao();
                spareParts = sparePartDao.getSparePartTitle(spareId).toArray(new SparePart[0]);
            }
        };
        threadToShowSparePart.start();
        try {
            threadToShowSparePart.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // init and set spare part adapter
        SparePartAdapter sparePartAdapter = new SparePartAdapter(SparePartActivity.this, spareParts);
        sparePartListView.setAdapter(sparePartAdapter);
        // for clickable items
        sparePartListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentToSparePart = new Intent(SparePartActivity.this, SparePartInfo.class);
            intentToSparePart.putExtra(Constants.ID, sparePartAdapter.getItem(position).getId());
            startActivity(intentToSparePart);
        });
        // close activity
        Button finishSparePartButton = findViewById(R.id.finish_spare_part);
        finishSparePartButton.setOnClickListener(v -> finish());
    }
}