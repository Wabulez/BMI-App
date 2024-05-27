package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class LoginActivity extends AppCompatActivity {
    EditText Name;
    String EnteredName;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MobileAds.initialize(this, "ca-app-pub-8528184830344634~4609143942");
        adView=findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        Name=findViewById(R.id.EnteredName);
    }
    public void NextPage (View x) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        EnteredName=Name.getText().toString();
        intent.putExtra("Entered_Name",EnteredName);
        if (Name.length() == 0) {
            Toast.makeText(getApplicationContext(),"Please enter a name before proceeding",Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }

    }
    public void Return (View x) {
        Intent intent = new Intent(LoginActivity.this, Menu.class);
        startActivity(intent);
        finish();
    }
}
