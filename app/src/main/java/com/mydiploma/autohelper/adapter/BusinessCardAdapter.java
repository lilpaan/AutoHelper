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
import com.mydiploma.autohelper.entity.BusinessCard;

public class BusinessCardAdapter extends ArrayAdapter<BusinessCard> {
    public BusinessCardAdapter(@NonNull Context context, @NonNull BusinessCard[] objects) {
        super(context, R.layout.business_card_item, objects);
    }
    @SuppressLint(Constants.INFLATE_PARAMS)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BusinessCard businessCard = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.business_card_item, null);
        }
        convertView.setBackgroundColor(Color.parseColor(Constants.ADAPTER_COLOR));
        ((TextView) convertView.findViewById(R.id.added_business_cards)).setText(businessCard.getAddress());
        return convertView;
    }
}