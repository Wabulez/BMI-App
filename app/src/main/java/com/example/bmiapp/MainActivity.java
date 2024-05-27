package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    TextView Height;
    TextView Weight;
    SeekBar SeekHeight;
    SeekBar SeekWeight;
    String Name;
    ImageButton Continue;
    EditText Age;
    String enteredAge;
    RadioButton Male;
    RadioButton Female;
    RadioGroup gender;
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-8528184830344634~4609143942");
        adView=findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Height=findViewById(R.id.HeightAnswer);
        Weight=findViewById(R.id.WeightAnswer);
        SeekHeight=findViewById(R.id.HeightSeekBar);
        SeekWeight=findViewById(R.id.WeightSeekBar);
        Continue=findViewById(R.id.imageButton);
        Age=findViewById(R.id.Age);
        Male=findViewById(R.id.RadioButton_Male);
        Female=findViewById(R.id.RadioButton_Female);
        gender=findViewById(R.id.radioGroup);


        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Male.isChecked()) {
                    Male.setTextColor(Color.RED);
                    Female.setTextColor(Color.WHITE);
                }
            }
        });

        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Female.isChecked()) {
                    Female.setTextColor(Color.RED);
                    Male.setTextColor(Color.WHITE);
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Name=bundle.getString("Entered_Name");
        }

        SeekHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Height.setText(i + "cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Weight.setText(i + "Kgs");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    public void GoToResults(View x) {
        Intent intent = new Intent(MainActivity.this,ResultsActivity.class);
        intent.putExtra("Entered_Height",SeekHeight.getProgress() + "");
        intent.putExtra("Entered_Weight",SeekWeight.getProgress() + "");
        intent.putExtra("Entered_Name", Name);
        intent.putExtra("Entered_Age",enteredAge=Age.getText().toString());
        if (Female.isChecked()) {
            intent.putExtra("Entered_Gender", Female.getText().toString());
        } else {
            intent.putExtra("Entered_Gender", Male.getText().toString());
        }

        if (Height.length() == 0 || Weight.length() == 00 || Age.length() == 0) {
            Toast.makeText(getApplicationContext(),"Please make sure you have entered a value for Height, Weight and Age",Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
            finish();
        }
    }

}
