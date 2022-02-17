package com.mydiploma.autohelper.ui.car;

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
import com.mydiploma.autohelper.database.SparePartDatabase;
import com.mydiploma.autohelper.entity.SparePart;

public class SparePartActivity extends AppCompatActivity {
    SparePartDatabase sparePartDatabase;
    SparePartDao sparePartDao;
    SparePart[] spareParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spare_part_activity);
        long id = getIntent().getLongExtra(Constants.CAR_ID_IN_SPARE_PART, 0);
        Button addSparePart = findViewById(R.id.add_spare_part_button);
        ListView sparePartListView = findViewById(R.id.added_spare_part_list);
        // add spare part
        addSparePart.setOnClickListener(v -> {
            Intent intentToAddSparePart
                    = new Intent(SparePartActivity.this, AddSparePartActivity.class);
            intentToAddSparePart.putExtra(Constants.CAR_ID_IN_SPARE_PART, id);
            startActivity(intentToAddSparePart);
        });
        // thread to show spare part items
        Thread threadToShowSparePart = new Thread(){
            @Override
            public void run() {
                sparePartDatabase = Room.databaseBuilder(SparePartActivity.this, SparePartDatabase.class,
                        Constants.SPARE_PART).build();
                sparePartDao = sparePartDatabase.sparePartDao();
                spareParts = sparePartDao.getSparePartTitle(id).toArray(new SparePart[0]);
            }
        };
        threadToShowSparePart.start();
        try {
            threadToShowSparePart.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // init and set spare part adapter
        SparePartAdapter sparePartAdapter= new SparePartAdapter(SparePartActivity.this, spareParts);
        sparePartListView.setAdapter(sparePartAdapter);
        // close activity
        Button finishSparePartButton = findViewById(R.id.finish_spare_part);
        finishSparePartButton.setOnClickListener(v -> finish());
    }
}