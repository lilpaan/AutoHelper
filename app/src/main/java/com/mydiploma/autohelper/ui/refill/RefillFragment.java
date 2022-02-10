package com.mydiploma.autohelper.ui.refill;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.databinding.FragmentNotificationsBinding;
import com.mydiploma.autohelper.ui.car.AddCarActivity;

public class RefillFragment extends Fragment {

    private RefillViewModel refillViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        refillViewModel =
                new ViewModelProvider(this).get(RefillViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button button = root.findViewById(R.id.show_map);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(root.getContext(), map.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}