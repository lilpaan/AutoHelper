package com.mydiploma.autohelper.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.adapter.CarAdapter;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.databinding.FragmentDashboardBinding;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.util.CarUtil;

import java.util.Objects;

public class CarFragment extends Fragment {
    private FragmentDashboardBinding binding;
    ListView carListView;
    Car[] cars;
    CarAdapter carAdapter;
    int carCount;
    ImageView carListIsEmpty;
    ScrollView carCountScrollView;
    CarDatabase carDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        carCountScrollView = root.findViewById(R.id.car_list_scroll_view);
        carListIsEmpty = root.findViewById(R.id.car_list_empty_image);
        Button addCarButton = root.findViewById(R.id.add_car_button);
        carListView = root.findViewById(R.id.added_car_list);

        // open DB
              carDatabase = Room.databaseBuilder(requireContext(), CarDatabase.class,
                Constants.CAR).build();
/*                carDatabase = Room.databaseBuilder(requireContext(),
                        CarDatabase.class, Constants.CAR)
                        .fallbackToDestructiveMigration()
                        .build();*/

        cars = CarUtil.showUserCar(carDatabase);

        // configure adapter
        carAdapter = new CarAdapter(requireContext(), cars);
        carListView.setAdapter(carAdapter);

        // for clickable items
        carListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentToCarInfo = new Intent(root.getContext(), CarInfo.class);
            intentToCarInfo.putExtra(Constants.ID, carAdapter.getItem(position).getId());
            startActivityForResult(intentToCarInfo, 1);
        });

        carCount = CarUtil.getCarCount(carDatabase);

        // if car list is empty
        if (carCount == 0) {
            carCountScrollView.setVisibility(View.INVISIBLE);
            carListIsEmpty.setVisibility(View.VISIBLE);
        }

        // button to add new car
        addCarButton.setOnClickListener(v -> {
            Intent intentToAddCar = new Intent(root.getContext(), AddCarActivity.class);
            startActivityForResult(intentToAddCar, 1);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        cars = CarUtil.showUserCar(carDatabase);
        // configure adapter
        carAdapter = new CarAdapter(requireContext(), cars);
        carListView.setAdapter(carAdapter);
        carListView.invalidateViews();
    }

}
