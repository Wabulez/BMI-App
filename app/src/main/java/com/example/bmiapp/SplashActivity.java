package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity {
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MobileAds.initialize(this, "ca-app-pub-8528184830344634~4609143942");
        adView=findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);

        Handler timer=new Handler();
        Runnable background=new Runnable() {
            @Override
            public void run() {
                PerformIntent();
            }
        };
        timer.postDelayed(background,2000);
    }
    public void PerformIntent () {
        Intent intent = new Intent(SplashActivity.this, Menu.class);
        startActivity(intent);
        finish();
    }
}
