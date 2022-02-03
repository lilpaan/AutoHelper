package com.mydiploma.autohelper.ui.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.mydiploma.autohelper.R;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        Button ok = findViewById(R.id.saveCarButton);
        Button cancelAdd = findViewById(R.id.cancelAddCarButton);
        ok.setOnClickListener(v -> {

        });
        cancelAdd.setOnClickListener(v -> {
            finish();
        });
    }
}