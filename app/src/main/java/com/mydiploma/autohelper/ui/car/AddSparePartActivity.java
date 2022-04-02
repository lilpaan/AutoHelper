package com.mydiploma.autohelper.ui.car;

import static android.graphics.Color.WHITE;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.SparePart;
import com.mydiploma.autohelper.util.SparePartUtil;

import java.util.Calendar;

public class AddSparePartActivity extends AppCompatActivity {
    boolean success;
    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spare_part);
        long id = getIntent().getLongExtra(Constants.ID, 0);
        Button ok = findViewById(R.id.save_spare_part_button);
        Button cancelAdd = findViewById(R.id.cancel_add_spare_part_button);

        // save new spare part
        ok.setOnClickListener(v -> {
            // entrance into carDatabase
            CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR).build();
            // rebase db code
/*            CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(),
                    CarDatabase.class, Constants.CAR)
                    .fallbackToDestructiveMigration()
                    .build();*/

            SparePart sparePart = new SparePart();
            // take values from fields
            sparePart.setCarID(id);
            sparePart.setType(((EditText) findViewById(R.id.input_spare_part_type)).getText().toString());
            sparePart.setMaker(((EditText) findViewById(R.id.input_spare_part_maker)).getText().toString());
            sparePart.setInstallationDate(dateAndTime.getTime());
            // execute adding
            success = SparePartUtil.addSparePart(carDatabase, sparePart);
            if(success) {
                setResult(1);
                finish();
            }
        });
        cancelAdd.setOnClickListener(v -> finish());
    }

    private void setDateOnTextView() {
        Button button = findViewById(R.id.spare_part_date_button);
        button.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        button.setTextColor(WHITE);
    }

    public void setSparePartDate(View v) {
        new DatePickerDialog(AddSparePartActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = (view, year, monthOfYear, dayOfMonth) -> {
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, monthOfYear);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setDateOnTextView();
    };

}
