package com.thenewboston.seth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 22707561 on 12/5/2014.
 */
public class SQLiteExample extends Activity implements View.OnClickListener{
    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        sqlUpdate = (Button)findViewById(R.id.bSQLUpdate);
        sqlView = (Button)findViewById(R.id.bSQLopenView);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
        sqlName = (EditText)findViewById(R.id.etSQLName);
        sqlHotness = (EditText)findViewById(R.id.etSQLHotness);
        sqlModify = (Button)findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button)findViewById(R.id.bGetInfo);
        sqlDelete = (Button)findViewById(R.id.bSQLdelete);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
        sqlRow = (EditText)findViewById(R.id.etSQLrowInfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e){
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck Yea!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.bSQLopenView:
                Intent i = new Intent("com.thenewboston.seth.SQLVIEW");
                startActivity(i);
                break;
        }
    }
}
