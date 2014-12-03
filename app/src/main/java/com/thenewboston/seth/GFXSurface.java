package com.thenewboston.seth;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by 22707561 on 12/2/2014.
 */
public class GFXSurface extends Activity implements View.OnTouchListener {

    MyBringBackSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY, animateX, animateY, scaledX, scaledY, aniX, aniY;
    Bitmap test, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = 0;
        dY = 0;
        animateX = 0;
        animateY = 0;
        scaledX = 0;
        scaledY = 0;
        aniX = 0;
        aniY = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.baseball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //set fps
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            // case finger pressed down
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
                break;
            // case finger pressed up
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                fY = fY - sY;
                scaledX = dX / 30;
                scaledY = dY / 30;
                x = 0;
                y = 0;
                break;
        }
        //returning true will track finger movement
        return true;
    }

    /**
     * Created by 22707561 on 12/2/2014.
     */
    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        Boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();

        }

        public void pause(){
            isRunning = false;
            while (true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning){
                if(!ourHolder.getSurface().isValid()){
                    continue;
                }

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);
                if (x != 0 && y != 0) {
                    canvas.drawBitmap(test, x - test.getWidth()/2, y - test.getHeight()/2, null);
                }
                if (sX != 0 && sY != 0) {

                    canvas.drawBitmap(plus, sX - plus.getWidth()/2, sY - plus.getHeight()/2, null);
                }
                if (fX != 0 && fY != 0) {
                    canvas.drawBitmap(test, fX - test.getWidth()/2 - aniX, fY - test.getHeight()/2 - aniY, null);
                    canvas.drawBitmap(plus, fX - plus.getWidth()/2, fY - plus.getHeight()/2, null);
                }
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

}
