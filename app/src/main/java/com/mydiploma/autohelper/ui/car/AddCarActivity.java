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
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

import java.util.Calendar;

public class AddCarActivity extends AppCompatActivity {
    boolean success;
    Calendar dateAndTime = Calendar.getInstance();
    static CarDao carDao;

    //Button button = findViewById(R.id.dateButton);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_activity);
        Button ok = findViewById(R.id.save_car_button);
        Button cancelAdd = findViewById(R.id.cancel_add_car_button);
        //currentDateTime = findViewById(R.id.currentDateTime);

        // open DB
        CarDatabase carDatabase = Room.databaseBuilder(getApplicationContext(), CarDatabase.class,
                Constants.CAR).build();
/*                carDatabase = Room.databaseBuilder(requireActivity(),
                        CarDatabase.class, Constants.CAR)
                        .fallbackToDestructiveMigration()
                        .build();*/

        // save new car
        ok.setOnClickListener(v -> {
            // add new car
            success = addNewCar(carDatabase);
            if(success) {
                this.finish();
            }
        });

        // cancel
        cancelAdd.setOnClickListener(v -> finish());
    }

    private void setDateOnTextView() {
        Button button = findViewById(R.id.dateButton);
        button.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        button.setTextColor(WHITE);
    }

    public void setDate(View v) {
        new DatePickerDialog(AddCarActivity.this, d,
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

    public boolean addNewCar(CarDatabase carDatabase) {
        boolean isCarAdded;
        Car car = new Car();
        car.setMaker (((EditText) findViewById(R.id.input_maker)).getText().toString());
        car.setModel (((EditText) findViewById(R.id.input_model)).getText().toString());
        car.setEngineVolume (Float.parseFloat(((EditText) findViewById(R.id.input_engine_volume)).getText().toString()));
        car.setTransmission (((EditText) findViewById(R.id.input_type_of_transmission)).getText().toString());
        car.setColor (((EditText) findViewById(R.id.input_color)).getText().toString());
        car.setProductionYear (Integer.parseInt(((EditText) findViewById(R.id.input_production_year)).getText().toString()));
        car.setCurrentOilBrand (((EditText) findViewById(R.id.input_current_oil_brand)).getText().toString());
        car.setInsuranceRunOutDate(dateAndTime.getTime());
        // carSaveThread to save car into db
        carDao = carDatabase.carDao();
        Thread carSaveThread = new Thread() {
            @Override
            public void run() {
                carDao.insert(car);
            }
        };
        try {
            carSaveThread.start();
            isCarAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
            isCarAdded = false;
        }
        return isCarAdded;
    }

}