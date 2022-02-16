package com.mydiploma.autohelper.ui.card;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.DiscountCardDao;
import com.mydiploma.autohelper.database.DiscountCardDatabase;
import com.mydiploma.autohelper.entity.DiscountCard;

public class DiscountCardInfo extends AppCompatActivity {
    DiscountCardDatabase discountCardDatabase;
    DiscountCardDao discountCardDao;
    DiscountCard discountCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_card_info);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        // thread to take discount info
        Thread getDiscountInfoThread = new Thread(){
            @Override
            public void run() {
                discountCardDatabase = Room.databaseBuilder(getApplicationContext(), DiscountCardDatabase.class,
                        Constants.DISCOUNT_CARD).build();
                discountCardDao = discountCardDatabase.discountCardDao();
                discountCard = discountCardDao.getById(id);
            }
        };
        getDiscountInfoThread.start();
        try {
            getDiscountInfoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // tw for output info
        TextView viewForNumber = findViewById(R.id.view_for_number);
        TextView viewForNfc = findViewById(R.id.view_for_nfc);
        TextView viewForBarcode = findViewById(R.id.view_for_barcode);
        // place info to tw
        viewForNumber.setText(String.valueOf(discountCard.getNumber()));
        viewForNfc.setText(discountCard.getNfc());
        viewForBarcode.setText(discountCard.getBarcode());
        // buttons
        Button button = findViewById(R.id.finish_discount_card);
        button.setOnClickListener(v -> finish());
        Button delete = findViewById(R.id.delete_discount_card);
        delete.setOnClickListener(v -> {
            //thread to delete car
            Thread deleteThread = new Thread(){
                @Override
                public void run() {
                    discountCardDatabase = Room.databaseBuilder(getApplicationContext(), DiscountCardDatabase.class,
                            Constants.DISCOUNT_CARD).build();
                    discountCardDao = discountCardDatabase.discountCardDao();
                    discountCardDao.delete(discountCard);
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
