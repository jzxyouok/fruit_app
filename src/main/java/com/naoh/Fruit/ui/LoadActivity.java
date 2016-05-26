package com.naoh.Fruit.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.dao.util.TestService;

public class LoadActivity extends Activity implements View.OnClickListener {
    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private Button login,reg;
    private Button tdb;


    //int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
//        attempts = (TextView) findViewById(R.id.textView5);
        login = (Button) findViewById(R.id.button1);
        login.setOnClickListener(this);
//        reg = (Button)findViewById(R.id.button2);
//        reg.setOnClickListener(this);
        tdb = (Button)findViewById(R.id.button3);
        tdb.setOnClickListener(this);

        // attempts.setText(Integer.toString(counter));
    }

    public void login() {
        if (username.getText().toString().equals("users") &&
                password.getText().toString().equals("users")) {
            Intent intent;
            intent = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Intent intent;
            intent = new Intent(LoadActivity.this, AdminActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "[请核对后输入正确信息]", Toast.LENGTH_SHORT).show();
        }
    }

    public void testdb()
    {
        Toast.makeText(this, String.valueOf(new TestService(LoadActivity.this).getCategoryCount()), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.button1:

//            if (username.getText().toString().equals("users") &&
//                    password.getText().toString().equals("users")) {
//                Intent intent;
//                intent = new Intent(LoadActivity.this, MainActivity.class);
//                startActivity(intent);
//            } else if (username.getText().toString().equals("admin") &&
//                    password.getText().toString().equals("admin")) {
//                Intent intent;
//                intent = new Intent(LoadActivity.this, AdminActivity.class);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "[请核对后输入正确信息]", Toast.LENGTH_SHORT).show();
//            }
                    login();
                break;
            case R.id.button3:
                testdb();
                break;
//            case R.id.button2:
//                Toast.makeText(this, "[尚未开放注册，敬请等待]", Toast.LENGTH_SHORT).show();
//                break;
        }
    }
}
