package com.thenewboston.seth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by 22707561 on 12/4/2014.
 */
public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper flippy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper)findViewById(R.id.viewFlipper1);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(500);
        flippy.startFlipping();
    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
