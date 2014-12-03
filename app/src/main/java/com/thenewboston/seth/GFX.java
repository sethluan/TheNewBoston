package com.thenewboston.seth;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;

import com.thenewboston.seth.MyBringBack;

/**
 * Created by 22707561 on 12/2/2014.
 */
public class GFX extends Activity{
    MyBringBack ourView;
    PowerManager.WakeLock wL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wake-lock
        PowerManager pM = (PowerManager)getSystemService(POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        wL.acquire();
        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }
}
