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
        // for open discount db
        ok.setOnClickListener(v -> {
            DiscountCardDatabase db =  Room.databaseBuilder(getApplicationContext(),
                    DiscountCardDatabase.class, Constants.DISCOUNT_CARD).build();
            // code to rebase db
/*
            DiscountCardDatabase db = Room.databaseBuilder(getApplicationContext(),
                    DiscountCardDatabase.class, Constants.DISCOUNT_CARD)
                    .fallbackToDestructiveMigration()
                    .build();
*/
            DiscountCard discountCard = new DiscountCard();
            // read input info
            discountCard.setNumber(((EditText) findViewById(R.id.input_number)).getText().toString());
            discountCard.setNfc(((EditText) findViewById(R.id.input_nfc)).getText().toString());
            discountCard.setBarcode(((EditText) findViewById(R.id.input_barcode)).getText().toString());
            DiscountCardDao discountCardDao = db.discountCardDao();
            // thread to save car
            Thread saveDiscountThread = new Thread(){
                @Override
                public void run() {
                    discountCardDao.insert(discountCard);
                }
            };
            saveDiscountThread.start();

/*            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navigation_dashboard, getFragmentManager().findFragmentById(R.id.frag));
            fragmentTransaction.commit(); */

            finish();
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

}