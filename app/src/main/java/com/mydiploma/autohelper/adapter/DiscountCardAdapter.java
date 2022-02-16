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
import com.mydiploma.autohelper.entity.DiscountCard;

public class DiscountCardAdapter extends ArrayAdapter<DiscountCard> {
    public DiscountCardAdapter(@NonNull Context context, @NonNull DiscountCard[] objects) {
        super(context, R.layout.discount_card_item, objects);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DiscountCard discountCard = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.discount_card_item, null);
        }
        convertView.setBackgroundColor(Color.parseColor(Constants.ADAPTER_COLOR));
        ((TextView) convertView.findViewById(R.id.added_discount_cards)).setText(discountCard.getNumber());
        return convertView;
    }

}
