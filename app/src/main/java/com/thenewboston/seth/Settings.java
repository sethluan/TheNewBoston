package com.thenewboston.seth;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 22707561 on 11/20/2014.
 */
public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        //getFragmentManager().beginTransaction().replace(R.id.fmPrefs, new Prefs()).commit();
    }
}
