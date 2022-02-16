package com.mydiploma.autohelper.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.adapter.CarAdapter;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.databinding.FragmentDashboardBinding;
import com.mydiploma.autohelper.entity.Car;

public class CarFragment extends Fragment {
    private FragmentDashboardBinding binding;
    CarDatabase carDatabase;
    CarDao carDao;
    Car[] cars;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView carListView = root.findViewById(R.id.added_car_list);
        Thread carGetTitleThread = new Thread(){
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(requireContext(), CarDatabase.class,
                        Constants.CAR).build();
                carDao = carDatabase.carDao();
                cars = carDao.getCarTitle().toArray(new Car[0]);
            }
        };
        carGetTitleThread.start();
        try {
            carGetTitleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CarAdapter carAdapter = new CarAdapter(requireContext(), cars);
        carListView.setAdapter(carAdapter);
        carListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentToCarInfo = new Intent(root.getContext(), CarInfo.class);
            intentToCarInfo.putExtra(Constants.ID, carAdapter.getItem(position).getId());
            startActivity(intentToCarInfo);
        });
        Button addCarButton = root.findViewById(R.id.add_car_button);
        addCarButton.setOnClickListener(v -> {
            Intent intentToAddCar = new Intent(root.getContext(), AddCarActivity.class);
            startActivity(intentToAddCar);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}