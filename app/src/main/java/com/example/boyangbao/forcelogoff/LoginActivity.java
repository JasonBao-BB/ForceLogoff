package com.example.boyangbao.forcelogoff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEditText, passwordEditText;
    private CheckBox checkBox;
    private Button button;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        button = (Button) findViewById(R.id.button);
        preferences = getSharedPreferences("data",MODE_PRIVATE);

        rememberData();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (accountEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("123456")) {
                    Toast.makeText(LoginActivity.this,"LLLLLLL",Toast.LENGTH_SHORT).show();
                    if (checkBox.isChecked()) {
                        editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("account",accountEditText.getText().toString());
                        editor.putString("password",passwordEditText.getText().toString());
                        editor.putBoolean("pwd_remember",true);
                        editor.apply();
                    } else {
                        editor.clear();
                    }

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void rememberData() {
        boolean isRemember = preferences.getBoolean("pwd_remember",false);
        if (isRemember) {
            String user = preferences.getString("account","");
            String pwd = preferences.getString("password","");
            accountEditText.setText(user);
            passwordEditText.setText(pwd);
            checkBox.setChecked(true);
        }
    }


}
