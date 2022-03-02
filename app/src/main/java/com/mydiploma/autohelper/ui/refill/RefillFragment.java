package com.mydiploma.autohelper.ui.refill;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.databinding.FragmentNotificationsBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class RefillFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    MapView mapview;
    private static final int PERMISSION_REQUEST = 1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapKitFactory.initialize(requireActivity());
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Укажите имя activity вместо map.
        mapview = root.findViewById(R.id.mapview);
        MapKit mapKit = MapKitFactory.getInstance();
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
        } else {
            mapKit.createLocationManager().requestSingleUpdate(new LocationListener() {
                @Override
                public void onLocationUpdated(@NonNull Location location) {
                    Log.d("TagCheck", "LocationUpdated " + location.getPosition().getLongitude());
                    Log.d("TagCheck", "LocationUpdated " + location.getPosition().getLatitude());
                    mapview.getMap().move(
                            new CameraPosition(location.getPosition(), 16.0f, 0.0f, 0.0f),
                            new Animation(Animation.Type.SMOOTH, 0),
                            null);

                }

                @Override
                public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {

                }
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }
}