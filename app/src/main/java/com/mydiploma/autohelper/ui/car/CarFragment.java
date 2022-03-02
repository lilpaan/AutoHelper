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
    ListView carListView;
    CarAdapter carAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button addCarButton = root.findViewById(R.id.add_car_button);
        carListView = root.findViewById(R.id.added_car_list);
        // thread to place car title in item
        Thread carGetTitleThread = new Thread() {
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(requireContext(), CarDatabase.class,
                        Constants.CAR).build();
/*                carDatabase = Room.databaseBuilder(requireActivity(),
                        CarDatabase.class, Constants.CAR)
                        .fallbackToDestructiveMigration()
                        .build();*/
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
        // init adapter
        carAdapter = new CarAdapter(requireContext(), cars);
        carListView.setAdapter(carAdapter);
        // for clickable items
        carListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentToCarInfo = new Intent(root.getContext(), CarInfo.class);
            intentToCarInfo.putExtra(Constants.ID, carAdapter.getItem(position).getId());
            startActivity(intentToCarInfo);
        });
        // button to add new car
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
