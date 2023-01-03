package com.example.bloodshare4.bloodshare40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button buttonDonate;
    Button buttonFindBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonDonate = findViewById(R.id.buttondonate);
        buttonFindBlood = findViewById(R.id.buttonfindblood);

        buttonDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, Account2Activity.class);
                startActivity(in);
            }
        });

        buttonFindBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(in);
            }
        });
    }
}
