package com.example.bloodshare4.bloodshare40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity {

    double minDistance=9999999;
    TextView textDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("accountdetails");
        textDetails = findViewById(R.id.details);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {

                    Information information = com.example.bloodshare4.bloodshare40.Information.getInstance();
                    if(distFrom(information.getLatitude(),information.getLongitude(),Integer.parseInt(item_snapshot.child("latitude").getValue().toString()), Integer.parseInt(item_snapshot.child("longitude").getValue().toString())) < minDistance && item_snapshot.child("group").getValue().toString()==information.getGroup()){

                        textDetails.setText("Donor : "+item_snapshot.child("name").getValue().toString()+"\nLocation : "+
                                item_snapshot.child("latitude").getValue().toString()+ "  "+item_snapshot.child("longitude").getValue().toString()+"\nMobile No: "+item_snapshot.child("mobile").getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);

        return dist;
    }
}
