package com.thenewboston.seth;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;

/**
 * Created by 22707561 on 12/9/2014.
 */
public class Maps extends Activity {
    MapView map;
    long start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        map = (MapView)findViewById(R.id.mvMain);

    }


}
