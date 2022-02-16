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

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car car = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item, null);
        }
        convertView.setBackgroundColor(Color.parseColor(Constants.ADAPTER_COLOR));
        ((TextView) convertView.findViewById(R.id.added_cars)).setText(car.getModel());
        return convertView;
    }

}

