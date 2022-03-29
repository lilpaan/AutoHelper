package com.mydiploma.autohelper.ui.refill;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.RefillDao;
import com.mydiploma.autohelper.database.RefillDatabase;
import com.mydiploma.autohelper.entity.Refill;
import com.mydiploma.autohelper.util.RefillUtil;

public class RefillInfo extends AppCompatActivity {

    RefillDatabase refillDatabase;
    RefillDao refillDao;
    Refill refill;
    boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refill_info);

        Long id = getIntent().getLongExtra(Constants.ID, 0);

        // open DB
        refillDatabase = Room.databaseBuilder(getApplicationContext(), RefillDatabase.class,
                Constants.REFILL).build();

        // show refill info func
        showRefillInfo(refillDatabase, id);

        // finishRefillButton for finish activity
        Button finishRefillButton = findViewById(R.id.finish_refill_info);
        finishRefillButton.setOnClickListener(v -> finish());

        // button for delete refill
        Button delete = findViewById(R.id.delete_refill_button);

        // thread to delete refill
        delete.setOnClickListener(v -> {
            success = RefillUtil.deleteRefill(refillDatabase, refill);
            if (success) {
                finish();
            }
        });
    }
    public void showRefillInfo(RefillDatabase refillDatabase, Long id) {
        // thread to get refill info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
                refillDao = refillDatabase.refillDao();
                refill = refillDao.getById(id);
            }
        };
        threadToGetCarInfo.start();
        try {
            threadToGetCarInfo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // text views for output info
        TextView viewForRefillInfoName = findViewById(R.id.view_for_refill_info_name);
        TextView viewForRefillInfoAddress = findViewById(R.id.view_for_refill_info_address);


        // place info to text views
        viewForRefillInfoName.setText(refill.getName());
        viewForRefillInfoAddress.setText(refill.getAddress());
    }
}
