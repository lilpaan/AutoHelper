package com.mydiploma.autohelper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CarAdapter extends ArrayAdapter<Car>{

        public CarAdapter(@NonNull Context context, @NonNull Car[] objects) {
            super(context, R.layout.car_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Car car = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item, null);
            }
            if (position%2==0){
                convertView.setBackgroundColor(Color.argb(190, 127, 255, 0));
            } else {
                convertView.setBackgroundColor(Color.argb(190, 152, 251, 152));
            }
            ((TextView) convertView.findViewById(R.id.added_cars)).setText(car.getModel());
            return convertView;
        }
    }

