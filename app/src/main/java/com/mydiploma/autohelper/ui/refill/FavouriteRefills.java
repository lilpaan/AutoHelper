package com.mydiploma.autohelper.ui.refill;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.adapter.RefillAdapter;
import com.mydiploma.autohelper.database.RefillDatabase;
import com.mydiploma.autohelper.entity.Refill;
import com.mydiploma.autohelper.util.RefillUtil;

public class FavouriteRefills extends AppCompatActivity {

    RefillDatabase refillDatabase;
    ListView refillListView;
    Refill[] refills;
    RefillAdapter refillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_refills);
        // open DB
        refillDatabase = Room.databaseBuilder(getApplicationContext(), RefillDatabase.class,
                Constants.REFILL).build();
/*                refillDatabase = Room.databaseBuilder(getApplicationContext()(),
                        RefillDatabase.class, Constants.REFILL)
                        .fallbackToDestructiveMigration()
                        .build();*/

        refills = RefillUtil.showRefills(refillDatabase);
        refillListView = findViewById(R.id.refill_list_list_view);

        // configure adapter
        refillAdapter = new RefillAdapter(getApplicationContext(), refills);
        refillListView.setAdapter(refillAdapter);

/*        // for clickable items
        refillListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentToCarInfo = new Intent(getApplicationContext(), RefillInfo.class);
            intentToCarInfo.putExtra(Constants.ID, refillAdapter.getItem(position).getId());
            startActivity(intentToCarInfo);
        });*/


    }
}