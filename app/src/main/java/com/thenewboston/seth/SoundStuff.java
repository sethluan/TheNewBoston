package com.thenewboston.seth;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 22707561 on 12/2/2014.
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener{
    SoundPool sp;
    MediaPlayer mp;
    int explosion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.splashsound, 1);
        mp = MediaPlayer.create(this, R.raw.splashsound);
    }

    @Override
    public void onClick(View v) {
        if (explosion != 0) {
            sp.play(explosion, 1, 1, 0, 0, 1);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
