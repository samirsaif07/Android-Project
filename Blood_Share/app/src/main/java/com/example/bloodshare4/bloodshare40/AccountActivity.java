package com.example.bloodshare4.bloodshare40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {

    Button buttonSubmit;
    EditText editFirstName;
    EditText editLastName;
    EditText editMobile;
    EditText editAddress;
    EditText editBirth;
    TextView textPrev;

    String mailSignUp;
    String passSignUp;
    String firstName;
    String lastName;
    String mobile;
    String address;
    String birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        buttonSubmit = (Button)findViewById(R.id.submitaccount);
        editFirstName = findViewById(R.id.firstname);
        editLastName = findViewById(R.id.lastname);
        editMobile = findViewById(R.id.mobile);
        editAddress = findViewById(R.id.address);
        editBirth = findViewById(R.id.birthdate);
        textPrev = findViewById(R.id.prevaccount);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editFirstName.getText().toString().equals("") || editLastName.getText().toString().equals("") || editMobile.getText().toString().equals("") || editAddress.getText().toString().equals("") || editBirth.getText().toString().equals("") ){
                    Toast.makeText(getBaseContext(),"You must fill all requirements", Toast.LENGTH_SHORT).show();

                }
                else {

                    firstName = editFirstName.getText().toString().trim();
                    lastName = editLastName.getText().toString().trim();
                    mobile = editMobile.getText().toString().trim();
                    address = editAddress.getText().toString().trim();
                    birth = editBirth.getText().toString().trim();

                    Bundle bundle = getIntent().getExtras();
                    bundle.putString("firstName",firstName);
                    bundle.putString("lastName",lastName);
                    bundle.putString("mobile",mobile);
                    bundle.putString("address",address);
                    bundle.putString("birth",birth);


                    Intent inAccount2 = new Intent(AccountActivity.this, Account2Activity.class);
                    inAccount2.putExtras(bundle);
                    startActivity(inAccount2);
                }
            }
        });

        textPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inSignUp = new Intent(AccountActivity.this, SignupActivity.class);
                startActivity(inSignUp);
            }
        });


    }
}
