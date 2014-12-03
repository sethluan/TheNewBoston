package com.thenewboston.seth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.thenewboston.seth.R;

/**
 * Created by 22707561 on 12/2/2014.
 */
public class MyBringBack extends View {
    Bitmap gBall;
    float changingY;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.baseball);
        changingY = 0;
        font = Typeface.createFromAsset(context.getAssets(), "DroidSansMono.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 254, 10, 50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(font);
        textPaint.setTextSize(50);

        canvas.drawText("mybringback", canvas.getWidth()/2, 200, textPaint);

        canvas.drawBitmap(gBall, canvas.getWidth() / 2, changingY, null);
        if (changingY < canvas.getHeight()){
            changingY += 10;
        }
        else
        {
            changingY = 0;
        }

        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect,ourBlue);

        invalidate();
    }
}
