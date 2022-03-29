package com.mydiploma.autohelper.ui.refill;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.mydiploma.autohelper.dao.RefillDao;
import com.mydiploma.autohelper.database.RefillDatabase;
import com.mydiploma.autohelper.databinding.FragmentNotificationsBinding;
import com.mydiploma.autohelper.entity.Refill;
import com.mydiploma.autohelper.ui.car.AddCarActivity;
import com.mydiploma.autohelper.ui.car.CarInfo;
import com.yandex.mapkit.GeoObject;
import com.yandex.mapkit.GeoObjectCollection;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.map.VisibleRegionUtils;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.search.SearchOptions;
import com.yandex.mapkit.search.Session;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RefillFragment extends Fragment implements UserLocationObjectListener,
        Session.SearchListener, CameraListener, GeoObjectTapListener {
    private FragmentNotificationsBinding binding;
    MapView mapView;
    Dialog refillInfo;
    private SearchManager searchManager;
    private UserLocationLayer userLocationLayer;
    boolean findSuccess;
    boolean showSuccess;
    RefillDao refillDao;
    boolean isRefillAdded;
    RefillDatabase refillDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // open DB
         refillDatabase = Room.databaseBuilder(requireContext(), RefillDatabase.class,
                Constants.REFILL).build();
/*                refillDatabase = Room.databaseBuilder(requireContext(),
                        RefillDatabase.class, Constants.REFILL)
                        .fallbackToDestructiveMigration()
                        .build();*/

        // init factories
        MapKitFactory.initialize(requireActivity());
        SearchFactory.initialize(requireActivity());
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button favouriteRefillsButton = root.findViewById(R.id.favourite_refills);
        mapView = root.findViewById(R.id.mapview);
        MapKit mapKit = MapKitFactory.getInstance();
        mapView.getMap().setRotateGesturesEnabled(false);
        mapView.getMap().move(new CameraPosition(new Point(0, 0), 15,
                0, 0));
        mapView.getMap().addCameraListener(this);
        mapView.getMap().addTapListener(this);
        findSuccess = findRefill(searchManager, mapView);
        if (findSuccess) {
            showSuccess = showRefillLocations(mapKit);
            if (!showSuccess) {
                Toast.makeText(requireActivity(), Constants.SHOW_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireActivity(), Constants.FIND_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
        }

        // move to fav refills list
        favouriteRefillsButton.setOnClickListener(v -> {
            Intent intentToFavRefills = new Intent(root.getContext(), FavouriteRefills.class);
            startActivity(intentToFavRefills);
        });

        return root;
    }

    @Override
    public void onObjectAdded(UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));
        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                requireContext(), R.drawable.user_arrow));
        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();
        pinIcon.setIcon(
                Constants.PIN,
                ImageProvider.fromResource(requireContext(), R.drawable.search_result),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );
        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);
    }

    @Override
    public void onCameraPositionChanged(@NonNull Map map, @NonNull CameraPosition cameraPosition,
                                        @NonNull CameraUpdateReason cameraUpdateReason,
                                        boolean finished) {
        if (finished) {
            findSuccess = findRefill(searchManager, mapView);
            if (!findSuccess) {
                Toast.makeText(requireActivity(),
                        Constants.FIND_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSearchResponse(@NonNull Response response) {
        MapObjectCollection mapObjects = mapView.getMap().getMapObjects();
        mapObjects.clear();
        for (GeoObjectCollection.Item searchResult : response.getCollection().getChildren()) {
            Point resultLocation = Objects.requireNonNull(searchResult.getObj())
                    .getGeometry().get(0).getPoint();
            if (resultLocation != null) {
                mapObjects.addPlacemark(
                        resultLocation,
                        ImageProvider.fromResource(requireActivity(), R.drawable.refill_tag));
            }
        }
    }

    @Override
    public void onSearchError(@NonNull Error error) {
        String errorMessage = getString(R.string.unknown_error_message);
        if (error instanceof RemoteError) {
            errorMessage = getString(R.string.remote_error_message);
        } else if (error instanceof NetworkError) {
            errorMessage = getString(R.string.network_error_message);
        }

        Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    public boolean findRefill(SearchManager searchManager, MapView mapView) {
        boolean findSuccess;
        try {
            searchManager.submit(
                    Constants.YANDEX_REFILL,
                    VisibleRegionUtils.toPolygon(mapView.getMap().getVisibleRegion()),
                    new SearchOptions(),
                    this);
            findSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            findSuccess = false;
        }
        return findSuccess;
    }

    public boolean showRefillLocations(MapKit mapKit) {
        boolean showSuccess;
        try {
            userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
            userLocationLayer.setVisible(true);
            userLocationLayer.setHeadingEnabled(true);
            userLocationLayer.setObjectListener(this);
            showSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            showSuccess = false;
        }
        return showSuccess;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onObjectRemoved(@NonNull UserLocationView view) {
    }

    @Override
    public void onObjectUpdated(@NonNull UserLocationView view, @NonNull ObjectEvent event) {
    }

    @Override
    public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
        GeoObject geoObject = geoObjectTapEvent.getGeoObject();
        System.out.println(geoObject.getName());
        if (geoObject.getName() == null) {
            return false;
        }
        refillInfo = new Dialog(getActivity());
        refillInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        refillInfo.setContentView(R.layout.refill_info);
        // for dialog design
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        TextView refillName = refillInfo.findViewById(R.id.view_for_refill_name);
        TextView refillAddress = refillInfo.findViewById(R.id.view_for_refill_address);
        refillAddress.setText(getAddress(geoObject));
        refillName.setText(geoObject.getName());
        lp.copyFrom(refillInfo.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        refillInfo.getWindow().setAttributes(lp);
        refillInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        refillInfo.show();
        Button addToFav = refillInfo.findViewById(R.id.add_refill);
        addToFav.setOnClickListener(v -> {
            Refill refill = new Refill();
            refill.setName(geoObject.getName());
            refill.setAddress(refillAddress.getText().toString());
            // carSaveThread to save refill into db
            refillDao = refillDatabase.refillDao();
            Thread carSaveThread = new Thread() {
                @Override
                public void run() {
                    refillDao.insert(refill);
                }
            };
            try {
                carSaveThread.start();
                isRefillAdded = true;
            } catch (Exception e) {
                e.printStackTrace();
                isRefillAdded = false;
            }
            refillInfo.cancel();
        });
        return false;
    }

    private String getAddress(GeoObject geoObject) {
        Geocoder geocoder = new Geocoder(requireActivity(), Locale.getDefault());
        String city;
        String requireAddress;
        List <com.yandex.mapkit.geometry.Geometry> hehe = geoObject.getGeometry();
        double latitude = Objects.requireNonNull(hehe.get(0).getPoint()).getLatitude();
        double longitude = Objects.requireNonNull(hehe.get(0).getPoint()).getLongitude();
        try {
            List<android.location.Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null) {
                android.location.Address returnedAddress = addresses.get(0);
                city = returnedAddress.getSubAdminArea();
                String citystreet = returnedAddress.getThoroughfare();
                System.out.println(citystreet);
                String cityNomer = returnedAddress.getSubThoroughfare();
                System.out.println(cityNomer);
                requireAddress = city + "\n" + citystreet + "\n" + cityNomer;
            } else {
                requireAddress = "Error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            requireAddress = "Error";
        }
        return requireAddress;
    }

}
