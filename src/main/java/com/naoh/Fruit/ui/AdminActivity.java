package com.naoh.Fruit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/5/2.
 */
public class AdminActivity extends Activity implements View.OnClickListener{

    private EditText editTextP = null;
    private EditText editTextN = null;
    private EditText editTextF = null;
    private TextView attempts;
    private Button sure,drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        editTextP = (EditText) findViewById(R.id.editTextP);
        editTextN = (EditText) findViewById(R.id.editTextN);
        editTextF = (EditText) findViewById(R.id.editTextF);
//        attempts = (TextView) findViewById(R.id.textView5);
        sure = (Button)findViewById(R.id.buttonS);
        drop = (Button)findViewById(R.id.buttonD);
        sure.setOnClickListener(this);
        drop.setOnClickListener(this);
        // attempts.setText(Integer.toString(counter));
    }


    @Override
    public void onClick(View arg0) {
        switch(arg0.getId()){
            case R.id.buttonS:
                  makesure();
                break;
            case R.id.buttonD:
                break;

        }

    }

    private void makesure() {
    }
}
