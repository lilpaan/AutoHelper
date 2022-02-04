package com.mydiploma.autohelper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.entity.Car;

public class CarAdapter extends ArrayAdapter<Car>{

        public CarAdapter(@NonNull Context context, @NonNull Car[] objects) {
            super(context, R.layout.car_item, objects);
        }

        @SuppressLint(Constants.INFLATE_PARAMS)
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Car car = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item, null);
            }
                convertView.setBackgroundColor(Color.argb(Constants.CAR_ADAPTER_COLOR_ALPHA,
                        Constants.CAR_ADAPTER_COLOR_RED, Constants.CAR_ADAPTER_COLOR_GREEN,
                        Constants.CAR_ADAPTER_COLOR_BLUE));
            ((TextView) convertView.findViewById(R.id.added_cars)).setText(car.getModel());
            return convertView;
        }
    }

