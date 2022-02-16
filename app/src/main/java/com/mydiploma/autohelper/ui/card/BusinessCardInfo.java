package com.mydiploma.autohelper.ui.card;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.BusinessCardDao;
import com.mydiploma.autohelper.database.BusinessCardDatabase;
import com.mydiploma.autohelper.entity.BusinessCard;

public class BusinessCardInfo extends AppCompatActivity {
    BusinessCardDatabase businessCardDatabase;
    BusinessCardDao businessCardDao;
    BusinessCard businessCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card_info);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        // thread to get car info
        Thread threadToGetCarInfo = new Thread(){
            @Override
            public void run() {
                businessCardDatabase = Room.databaseBuilder(getApplicationContext(), BusinessCardDatabase.class,
                        Constants.BUSINESS_CARD).build();
                businessCardDao = businessCardDatabase.businessCardDao();
                businessCard = businessCardDao.getById(id);
            }
        };
        threadToGetCarInfo.start();
        try {
            threadToGetCarInfo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // tw for output info
        TextView viewForPhoneNumber = findViewById(R.id.view_for_phone_number);
        TextView viewForAddress = findViewById(R.id.view_for_address);
        TextView viewForEmail = findViewById(R.id.view_for_email);
        TextView viewForSite = findViewById(R.id.view_for_site);

        // input into tw
        viewForPhoneNumber.setText(String.valueOf(businessCard.getPhoneNumber()));
        viewForAddress.setText(businessCard.getAddress());
        viewForEmail.setText(businessCard.getEmail());
        viewForSite.setText(businessCard.getSite());


        // buttons
        Button button = findViewById(R.id.finish_business_card);
        button.setOnClickListener(v -> finish());
        Button delete = findViewById(R.id.delete_business_card);
        delete.setOnClickListener(v -> {
            //thread to delete car
            Thread deleteThread = new Thread(){
                @Override
                public void run() {
                    businessCardDatabase = Room.databaseBuilder(getApplicationContext(), BusinessCardDatabase.class,
                            Constants.BUSINESS_CARD).build();
                    businessCardDao = businessCardDatabase.businessCardDao();
                    businessCardDao.delete(businessCard);
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
