package com.mydiploma.autohelper.ui.car;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.SparePart;
import com.mydiploma.autohelper.util.SparePartUtil;

public class AddSparePartActivity extends AppCompatActivity {
boolean success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        Button ok = findViewById(R.id.save_spare_part_button);
        Button cancelAdd = findViewById(R.id.cancel_add_spare_part_button);
        // save new spare part
        ok.setOnClickListener(v -> {
            // entrance into carDatabase
            CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR).build();
            // rebase db code
/*            CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR)
                    .fallbackToDestructiveMigration()
                    .build();*/
            SparePart sparePart = new SparePart();
            // take values from fields
            sparePart.setCarID(id);
            sparePart.setType(((EditText) findViewById(R.id.input_spare_part_type)).getText().toString());
            sparePart.setMaker(((EditText) findViewById(R.id.input_spare_part_maker)).getText().toString());
            sparePart.setInstallationDate(((EditText)
                    findViewById(R.id.input_spare_part_installation_date)).getText().toString());
            // execute adding
            success = SparePartUtil.addSparePart(carDatabase, sparePart);
            if(success) {
                finish();
            }
        });
        cancelAdd.setOnClickListener(v -> finish());
    }
}
