package com.thenewboston.seth;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 22707561 on 12/9/2014.
 */
public class StatusBar extends Activity implements View.OnClickListener {

    Button stat;
    NotificationManager nm;
    static final int uniqueID = 1394885;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);
        stat = (Button)findViewById(R.id.bStatusBar);
        stat.setOnClickListener(this);
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(uniqueID);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StatusBar.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        String body = "This is a message from Seth, thanks for your support";
        String title = "Seth L.";
        Notification n = new Notification(R.drawable.images, body, System.currentTimeMillis());
        n.setLatestEventInfo(this, title, body, pi);
        n.defaults = Notification.DEFAULT_ALL;
        nm.notify(uniqueID, n);
        finish();
    }
}
