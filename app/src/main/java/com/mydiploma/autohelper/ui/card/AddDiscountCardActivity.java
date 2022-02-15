package com.mydiploma.autohelper.ui.card;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.DiscountCardDao;
import com.mydiploma.autohelper.database.DiscountCardDatabase;
import com.mydiploma.autohelper.entity.DiscountCard;

public class AddDiscountCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount_card);
        Button ok = findViewById(R.id.saveDiscountCardButton);
        Button cancelAdd = findViewById(R.id.cancelAddDiscountCardButton);
        ok.setOnClickListener(v -> {


/*
            DiscountCardDatabase db = Room.databaseBuilder(getApplicationContext(),
                    DiscountCardDatabase.class, Constants.BUSINESS_CARD)
                    .fallbackToDestructiveMigration()
                    .build();
*/



            DiscountCardDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    DiscountCardDatabase.class, Constants.BUSINESS_CARD).build();




            DiscountCard discountCard = new DiscountCard();
            discountCard.setPhoneNumber(((EditText) findViewById(R.id.input_phone_number)).getText().toString());
            discountCard.setAddress(((EditText) findViewById(R.id.input_address)).getText().toString());
            discountCard.setEmail(((EditText) findViewById(R.id.input_email)).getText().toString());
            discountCard.setSite(((EditText) findViewById(R.id.input_site)).getText().toString());
            DiscountCardDao discountCardDao = db.discountCardDao();
            Thread thread = new Thread(){
                @Override
                public void run() {
                    discountCardDao.insert(discountCard);
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