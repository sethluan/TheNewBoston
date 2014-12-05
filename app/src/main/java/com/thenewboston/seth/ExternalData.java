package com.thenewboston.seth;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 22707561 on 12/4/2014.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView canWrite, canRead;
    private String state;
    boolean canW, canR;
    Spinner spinner;
    String [] paths = {"Music", "Pictures", "Download"};
    File path = null;
    File file = null;
    EditText saveFile;
    Button confirm, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView)findViewById(R.id.tvCanWrite);
        canRead = (TextView)findViewById(R.id.tvCanRead);
        confirm = (Button)findViewById(R.id.bConfirmSaveAs);
        save = (Button)findViewById(R.id.bSaveFile);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);
        saveFile = (EditText)findViewById(R.id.etSaveAs);

        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paths);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            //read and write
            canWrite.setText("true");
            canRead.setText("true");
            canW = canR = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canRead.setText("true");
            canW = false;
            canR = true;
        } else {
            canWrite.setText("false");
            canRead.setText("false");
            canW = canR = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int position = spinner.getSelectedItemPosition();
        switch (position){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);

                checkState();
                if (canW == canR == true){

                        //InputStream is = getResources().getDrawable(R.drawable.baseball);
                        AssetManager am = getAssets();
                    try {
                        InputStream is = am.open("baseball.png");
                        OutputStream os = new FileOutputStream(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path, f);
                
                break;
        }
    }
}
