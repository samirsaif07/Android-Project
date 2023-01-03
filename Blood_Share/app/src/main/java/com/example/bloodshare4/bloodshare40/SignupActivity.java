package com.example.bloodshare4.bloodshare40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button buttonSignUp;
    EditText editMailSignUp;
    EditText editPassSignUp;
    String mailSignUp;
    String passSignUp;
    TextView logIn;
    //DatabaseReference databaseReference;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        buttonSignUp = (Button) findViewById(R.id.buttonsignup);
        editMailSignUp = (EditText)findViewById(R.id.mailsignup);
        editPassSignUp = (EditText)findViewById(R.id.passsignup);
        mailSignUp = editMailSignUp.getText().toString().trim();
        passSignUp = editPassSignUp.getText().toString().trim();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String id = myRef.push().getKey();
        myRef.child(id).setValue("Hello World");


        //databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        buttonSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mailSignUp.trim()== "" || passSignUp.trim() ==""){
                    Toast.makeText(getBaseContext(),"You must enter both email and password", Toast.LENGTH_SHORT).show();
                }
                else {

                    Bundle bundle = new Bundle();
                    bundle.putString("mailSignUp",mailSignUp);
                    bundle.putString("passSignUp",passSignUp);


                    //databaseReference.push().setValue(user);
                    Intent inAccount = new Intent(SignupActivity.this, AccountActivity.class);
                    inAccount.putExtras(bundle);
                    startActivity(inAccount);
                }
            }
        });

        logIn = findViewById(R.id.textlogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inLogIn = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(inLogIn);
            }
        });



    }
}
