package com.mydiploma.autohelper.ui.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.databinding.FragmentHomeBinding;
import com.mydiploma.autohelper.databinding.FragmentNotificationsBinding;

public class CardFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);;
        View root = binding.getRoot();
        LinearLayout discountCardList = root.findViewById(R.id.discount_card_list);
        LinearLayout businessCardList = root.findViewById(R.id.business_card_list);
        Button businessCardButton = root.findViewById(R.id.business_card_button);
        businessCardButton.setOnClickListener(v -> {
            businessCardList.setVisibility(View.VISIBLE);
            discountCardList.setVisibility(View.GONE);
        });
        Button discountCardButton = root.findViewById(R.id.discount_card_button);
        discountCardButton.setOnClickListener(v -> {
            discountCardList.setVisibility(View.VISIBLE);
            businessCardList.setVisibility(View.GONE);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}