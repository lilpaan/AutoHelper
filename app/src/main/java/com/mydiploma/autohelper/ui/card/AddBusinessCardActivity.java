package com.mydiploma.autohelper.ui.card;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.BusinessCardDao;
import com.mydiploma.autohelper.database.BusinessCardDatabase;
import com.mydiploma.autohelper.entity.BusinessCard;

public class AddBusinessCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business_card);
        Button ok = findViewById(R.id.saveBusinessCardButton);
        Button cancelAdd = findViewById(R.id.cancelAddBusinessCardButton);
        // to open business db
        ok.setOnClickListener(v -> {
            BusinessCardDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    BusinessCardDatabase.class, Constants.BUSINESS_CARD).build();
            // code to rebase db
/*
            BusinessCardDatabase db = Room.databaseBuilder(getApplicationContext(),
                    BusinessCardDatabase.class, Constants.BUSINESS_CARD)
                    .fallbackToDestructiveMigration()
                    .build();
*/
            // get info from input fields
            BusinessCard businessCard = new BusinessCard();
            businessCard.setPhoneNumber(((EditText) findViewById(R.id.input_phone_number)).getText().toString());
            businessCard.setAddress(((EditText) findViewById(R.id.input_address)).getText().toString());
            businessCard.setEmail(((EditText) findViewById(R.id.input_email)).getText().toString());
            businessCard.setSite(((EditText) findViewById(R.id.input_site)).getText().toString());
            BusinessCardDao businessCardDao = db.businessCardDao();
            // thread to save business
            Thread saveBusinessThread = new Thread(){
                @Override
                public void run() {
                    businessCardDao.insert(businessCard);
                }
            };
            saveBusinessThread.start();
            setResult(1);
            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

}
