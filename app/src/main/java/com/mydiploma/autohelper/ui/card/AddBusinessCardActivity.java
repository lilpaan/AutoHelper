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
        ok.setOnClickListener(v -> {


/*
            BusinessCardDatabase db = Room.databaseBuilder(getApplicationContext(),
                    BusinessCardDatabase.class, Constants.BUSINESS_CARD)
                    .fallbackToDestructiveMigration()
                    .build();
*/



            BusinessCardDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    BusinessCardDatabase.class, Constants.BUSINESS_CARD).build();




            BusinessCard businessCard = new BusinessCard();
            businessCard.setPhoneNumber(((EditText) findViewById(R.id.input_phone_number)).getText().toString());
            businessCard.setAddress(((EditText) findViewById(R.id.input_address)).getText().toString());
            businessCard.setEmail(((EditText) findViewById(R.id.input_email)).getText().toString());
            businessCard.setSite(((EditText) findViewById(R.id.input_site)).getText().toString());
            BusinessCardDao businessCardDao = db.businessCardDao();
            Thread thread = new Thread(){
                @Override
                public void run() {
                    businessCardDao.insert(businessCard);
                }
            };
            thread.start();

/*            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navigation_dashboard, getFragmentManager().findFragmentById(R.id.frag));
            fragmentTransaction.commit(); */


            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

}
