package com.mydiploma.autohelper.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.mydiploma.autohelper.Car;
import com.mydiploma.autohelper.CarAdapter;
import com.mydiploma.autohelper.CarDao;
import com.mydiploma.autohelper.CarDatabase;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    CarDatabase carDatabase;
    CarDao carDao;
    Car[] cars;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView lv = root.findViewById(R.id.added_car_list);
        Thread thread = new Thread(){
            @Override
            public void run() {
                carDatabase = Room.databaseBuilder(requireContext(), CarDatabase.class, "car").build();
                carDao = carDatabase.carDao();
                cars = carDao.getCarTitle().toArray(new Car[0]);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CarAdapter adapter = new CarAdapter(requireContext(), cars);
        lv.setAdapter(adapter);
        Button button = root.findViewById(R.id.addCarButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), AddCarActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}