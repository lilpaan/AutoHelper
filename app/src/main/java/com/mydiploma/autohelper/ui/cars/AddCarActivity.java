package com.mydiploma.autohelper.ui.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mydiploma.autohelper.Car;
import com.mydiploma.autohelper.R;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        Button ok = findViewById(R.id.saveCarButton);
        Button cancelAdd = findViewById(R.id.cancelAddCarButton);
        ok.setOnClickListener(v -> {
/*            Car car = new Car();
            EditText edit = (EditText)findViewById(R.id.inputMaker);
            TextView tview = (TextView)findViewById(R.id.inputMaker);
            String result = edit.getText().toString();
            tview.setText(result);*/
/*            car.setMaker(findViewById(R.id.inputMaker).toString());
            car.setModel();*/
        });
        cancelAdd.setOnClickListener(v -> {
            finish();
        });
    }
}