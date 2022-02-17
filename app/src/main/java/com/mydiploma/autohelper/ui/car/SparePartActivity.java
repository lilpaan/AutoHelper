package com.mydiploma.autohelper.ui.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;

public class SparePartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spare_part_activity);
        long id = getIntent().getLongExtra(Constants.CAR_ID_IN_SPARE_PART, 0);
        Button addSparePart = findViewById(R.id.add_spare_part_button);
        // add spare part
        addSparePart.setOnClickListener(v -> {
            Intent intentToAddSparePart
                    = new Intent(SparePartActivity.this, AddSparePartActivity.class);
            intentToAddSparePart.putExtra(Constants.CAR_ID_IN_SPARE_PART, id);
            startActivity(intentToAddSparePart);
        });
        // close activity
        Button finishSparePartButton = findViewById(R.id.finish_spare_part);
        finishSparePartButton.setOnClickListener(v -> finish());
    }
}