package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.database.SparePartDatabase;
import com.mydiploma.autohelper.entity.SparePart;

public class AddSparePartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);
        long id = getIntent().getLongExtra(Constants.CAR_ID_IN_SPARE_PART, 0);
        Button ok = findViewById(R.id.save_spare_part_button);
        Button cancelAdd = findViewById(R.id.cancel_add_spare_part_button);
        // save new spare part
        ok.setOnClickListener(v -> {

            // entrance into sparePartDatabase
            SparePartDatabase sparePartDatabase = Room.databaseBuilder(getApplicationContext(),
                    SparePartDatabase.class, Constants.SPARE_PART).build();
            // rebase db code
/*            SparePartDatabase sparePartDatabase = Room.databaseBuilder(getApplicationContext(),
                    SparePartDatabase.class, Constants.SPARE_PART)
                    .fallbackToDestructiveMigration()
                    .build();*/


            SparePart sparePart = new SparePart();
            // take values from fields
            sparePart.setCarID(id);
            sparePart.setType(((EditText) findViewById(R.id.input_spare_part_type)).getText().toString());
            sparePart.setMaker(((EditText) findViewById(R.id.input_spare_part_maker)).getText().toString());
            sparePart.setInstallationDate(((EditText)
                    findViewById(R.id.input_spare_part_installation_date)).getText().toString());
            // sparePartSaveThread to save sparePart into db
            SparePartDao sparePartDao = sparePartDatabase.sparePartDao();
            Thread sparePartSaveThread = new Thread() {
                @Override
                public void run() {
                    sparePartDao.insert(sparePart);
                }
            };
            sparePartSaveThread.start();
/*            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navigation_dashboard, getFragmentManager().findFragmentById(R.id.frag));
            fragmentTransaction.commit();*/

            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }
}
