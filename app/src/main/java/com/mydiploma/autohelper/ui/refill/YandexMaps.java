package com.mydiploma.autohelper.ui.refill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class YandexMaps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(Constants.YANDEX_API_KEY);
        MapKitFactory.initialize(this);

        // Укажите имя activity вместо map.
        setContentView(R.layout.activity_map);
        MapView mapview = (MapView) findViewById(R.id.map_view);
        mapview.getMap().move(
                        new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 0),
                null);
    }
}