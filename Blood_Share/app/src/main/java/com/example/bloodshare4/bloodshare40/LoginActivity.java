package com.example.bloodshare4.bloodshare40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    Button buttonLogIn;
    EditText editMailLogIn;
    EditText editPassLogIn;
    String mailLogIn;
    String passLogIn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogIn =  findViewById(R.id.buttonlogin);
        editMailLogIn = findViewById(R.id.maillogin);
        editPassLogIn = findViewById(R.id.passlogin);
        mailLogIn = editMailLogIn.getText().toString();
        passLogIn = editPassLogIn.getText().toString();

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mailLogIn== "" || passLogIn==""){
                    Toast.makeText(getBaseContext(),"You must enter both email and password", Toast.LENGTH_SHORT).show();
                    editPassLogIn.setText("");
                }
            }
        });

        signUp = findViewById(R.id.textlogin);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(inSignUp);
            }
        });

    }
}
