package com.thenewboston.seth;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 22707561 on 12/9/2014.
 */
public class HttpExample extends Activity{

    TextView httpStuff;
    HttpClient client;
    JSONObject json;

    final static String URL = "https://api.foursquare.com/v2/venues/explore?";
    final static String CLIENT_ID = "4K213ITDZ20HNGIZDOR2GMO1IC20OV0DRIOY5KO41MVE3KGP";
    final static String CLIENT_SECRET = "4HWMRXRRLDPSCNCC0NCTKSK5TWGFJGGMPDDXJS2HDEJW12OX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpStuff = (TextView)findViewById(R.id.tvHttp);
        client = new DefaultHttpClient();
        new Read().execute("name");
    }

    public JSONObject suggestedPlace() throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        url.append("client_id=" + CLIENT_ID);
        url.append("&client_secret=" + CLIENT_SECRET);
        url.append("&v=20141219");
        url.append("&section=food");
        url.append("&ll=35,139");

        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            //debug by priting out the JSON
            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "foursquare.txt");
            //OutputStream os = new FileOutputStream(file);
            //os.write(data.getBytes());
            //os.close();

            JSONObject jsonObj = new JSONObject(data);
            JSONArray groups    = jsonObj.getJSONObject("response").getJSONArray("groups");
            //JSONArray arrayVenue = new JSONArray(data);
            JSONObject objGroup = groups.getJSONObject(0);
            JSONArray items = objGroup.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);
            JSONObject objVenue = item.getJSONObject("venue");

            return objVenue;
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                json = suggestedPlace();
                return json.getString(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            httpStuff.setText(s);
        }
    }
}
