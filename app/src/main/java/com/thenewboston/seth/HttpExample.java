package com.thenewboston.seth;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by 22707561 on 12/9/2014.
 */
public class HttpExample extends Activity{

    TextView httpStuff;
    HttpClient client;

    final static String URL = "http://api.twitter.com/1.1/statues/user_timeline.json?screen_name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView)findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
    }

    public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);

        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONArray timeline = new JSONArray(data);
            JSONObject last = timeline.getJSONObject(0);
            return last;
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT);
            return null;
        }
    }
}
