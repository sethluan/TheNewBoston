package com.thenewboston.seth;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 22707561 on 11/19/2014.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread, sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name", "Seth is...");
        String values = getData.getString("list", "4");
        if (values.contentEquals("1")) {
            question.setText(et);
        }

        //Bundle gotBasket = getIntent().getExtras();
        //gotBread = gotBasket.getString("key");
        //question.setText(gotBread);
    }

    private void initialize() {
        question = (TextView)findViewById(R.id.tvQuestion);
        test = (TextView)findViewById(R.id.tvTest);
        returnData = (Button)findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup)findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer", sendData);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rCrazy:
                sendData = "Probably right!";
                break;
            case R.id.rSexy:
                sendData = "Definitely right!";
                break;
            case R.id.rBoth:
                sendData = "Spot On!";
                break;
        }
        test.setText(sendData);
    }
}
