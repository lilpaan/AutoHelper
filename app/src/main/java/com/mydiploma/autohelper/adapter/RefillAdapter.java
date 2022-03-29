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
import com.mydiploma.autohelper.entity.Refill;

public class RefillAdapter extends ArrayAdapter<Refill>{

    public RefillAdapter(@NonNull Context context, @NonNull Refill[] objects) {
        super(context, R.layout.refill_item, objects);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Refill refill = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.refill_item, null);
        }
        convertView.setBackgroundColor(Color.parseColor(Constants.ADAPTER_COLOR));
        ((TextView) convertView.findViewById(R.id.added_refills)).setText(refill.getName());
        return convertView;
    }

}

