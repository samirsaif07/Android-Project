package com.example.bloodshare4.bloodshare40;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class Account2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LocationListener {


    String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    String group = "A+";
    TextView textLocation;
    LocationManager locationManager;
    double latitude, longitude;
    String provider;
    Button submit;

    String donor = "yes";

    DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account2);

        /*Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);*/

        /*if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            provider = LocationManager.GPS_PROVIDER;
        else
            provider = LocationManager.NETWORK_PROVIDER;*/


        textLocation = findViewById(R.id.textlocation);
        Spinner spinnerBlood = (Spinner) findViewById(R.id.spinnerbloodgroup);
        spinnerBlood.setOnItemSelectedListener(this);
        ArrayAdapter arrayBlood = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroups);
        arrayBlood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlood.setAdapter(arrayBlood);

        Location location = null;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //locationManager.requestLocationUpdates(provider, 0, 0, this);
        //onLocationChanged(location);

        submit = findViewById(R.id.buttonsubmitaccount2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                User user = new User();
                user.setEmail(bundle.getString("mailSignUp"));
                user.setPassword(bundle.getString("passSignUp"));
                user.setName(bundle.getString("firstName")+bundle.getString("lastName"));
                user.setMobile(bundle.getString("mobile"));
                user.setAddress(bundle.getString("address"));
                user.setBirthDate(bundle.getString("birth"));
                user.setDonor(donor);
                user.setGroup(group);
                user.setLongitude(longitude);
                user.setLatitude(latitude);

                databaseReference = FirebaseDatabase.getInstance().getReference("accountdetails");
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(user);

                Information information = com.example.bloodshare4.bloodshare40.Information.getInstance();
                information.setLatitude(latitude);
                information.setLongitude(longitude);
                information.setGroup(group);

                Intent intent = new Intent(Account2Activity.this, HomeActivity.class);
                startActivity(intent);

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        group = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), group, Toast.LENGTH_LONG);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onLocationChanged(Location location) {
        textLocation = (TextView) findViewById(R.id.textlocation);
        //latitude = location.getLatitude();
        //longitude= location.getLongitude();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        textLocation.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

}
