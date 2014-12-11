package com.thenewboston.seth;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 22707561 on 12/11/2014.
 */
public class XmlParsing extends Activity implements View.OnClickListener {

    static final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    TextView tv;
    EditText city, state;
    URL website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.xmlparsing);
        Button b = (Button)findViewById(R.id.bWeather);
        b.setOnClickListener(this);
        tv = (TextView)findViewById(R.id.tvWeather);
        city = (EditText)findViewById(R.id.etCity);
        state = (EditText)findViewById(R.id.etState);



    }

    @Override
    public void onClick(View v) {
        String c = city.getText().toString();
        String s = state.getText().toString();

        try {
            StringBuilder url = new StringBuilder(baseURL);
            url.append(c + "&mode=xml&units=metric");
            String fullUrl = url.toString();
            website = new URL(fullUrl);
            //getting xml reader to parse data
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();


            //xr.parse(new InputSource(website.openStream()));
            new openConnection().execute(xr);

        } catch (Exception e){
            e.printStackTrace();
            tv.setText("error");
        }
    }

    private class openConnection extends AsyncTask<XMLReader, Void, String> {
        @Override
        protected String doInBackground(XMLReader... params) {
            try {
                HandlingXMLStuff doingWork = new HandlingXMLStuff();
                params[0].setContentHandler(doingWork);
                params[0].parse(new InputSource(website.openStream()));
                return doingWork.getInformation();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            String information = s;
            tv.setText(information);
        }
    }
}
