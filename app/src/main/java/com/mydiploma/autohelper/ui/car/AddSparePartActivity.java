/*
package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.entity.SparePart;

public class AddSparePartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);
        Button ok = findViewById(R.id.save_spare_part_button);
        Button cancelAdd = findViewById(R.id.cancel_add_spare_part_button);
        // save new spare part
        ok.setOnClickListener(v -> {

            // entrance into sparePartDatabase
            SparePartDatabase sparePartDatabase =  Room.databaseBuilder(getApplicationContext(),
                    SparePartDatabase.class, Constants.SPARE_PART).build();
            // rebase db code
*/
/*
            SparePartDatabase sparePartDatabase = Room.databaseBuilder(getApplicationContext(),
                    SparePartDatabase.class, Constants.SPARE_PART)
                    .fallbackToDestructiveMigration()
                    .build();
*//*

            SparePart sparePart = new SparePart();
            // take values from fields
            sparePart.setCarID(id);
            sparePart.setModel (((EditText) findViewById(R.id.input_model)).getText().toString());
            sparePart.setEngineVolume (Float.parseFloat(((EditText) findViewById(R.id.input_engine_volume)).getText().toString()));
            sparePart.setTransmission (((EditText) findViewById(R.id.input_type_of_transmission)).getText().toString());
            sparePart.setColor (((EditText) findViewById(R.id.input_color)).getText().toString());
            sparePart.setProductionYear (Integer.parseInt(((EditText) findViewById(R.id.input_production_year)).getText().toString()));
            sparePart.setCurrentOilBrand (((EditText) findViewById(R.id.input_current_oil_brand)).getText().toString());
            sparePart.setInsuranceRunOutDate (((EditText) findViewById(R.id.input_insurance_run_out_date)).getText().toString());
            // carSaveThread to save sparePart into db
            CarDao carDao = sparePartDatabase.carDao();
            Thread carSaveThread = new Thread(){
                @Override
                public void run() {
                    carDao.insert(sparePart);
                }
            };
            carSaveThread.start();
*/
/*            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navigation_dashboard, getFragmentManager().findFragmentById(R.id.frag));
            fragmentTransaction.commit();*//*

            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }
    }
}*/
