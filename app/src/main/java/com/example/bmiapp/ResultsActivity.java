package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ResultsActivity extends AppCompatActivity {
    String Name;
    double Height;
    double Weight;
    TextView BMI;
    String Age;
    TextView EnteredAge;
    TextView Gender;
    String Gender_Chosen;
    TextView Advice;
    LinearLayout Background;
    TextView Fat;
    LinearLayout Layout;
    public View preView;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        MobileAds.initialize(this, "ca-app-pub-8528184830344634~4609143942");
        adView=findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        Bundle bundle = getIntent().getExtras();
        BMI=findViewById(R.id.BMI);
        Gender=findViewById(R.id.Gender);
        EnteredAge=findViewById(R.id.EnteredAge);
        Advice=findViewById(R.id.Advice);
        Background=findViewById(R.id.Background);
        Fat=findViewById(R.id.FatPercentage);
        Layout=findViewById(R.id.Layout);
        preView = getWindow().getDecorView();

        if (bundle != null) {
            Name=bundle.getString("Entered_Name");
            Height=Double.parseDouble(bundle.getString("Entered_Height"))/100;
            Weight=Double.parseDouble(bundle.getString("Entered_Weight"));
            Age=bundle.getString("Entered_Age");
            Gender_Chosen=bundle.getString("Entered_Gender");

            EnteredAge.setText("You are " + String.valueOf(Age) + " Years old");
            double Result = Weight / (Height * Height);
            BMI.setText(String.valueOf(String.format("%.2f", Result)));
            Gender.setText(Gender_Chosen);



            if (Integer.parseInt(Age) < 18 && Gender_Chosen.equals("Male")) {
                double result = (1.51 * Result) - (0.70 * Double.parseDouble(Age)) - (3.6 * 1) + 1.4;
                Fat.setText(""+String.valueOf(String.format("%.2f",result)) + "%");
                Fat.setTextColor(Color.BLUE);
            } else if (Integer.parseInt(Age) < 18 && Gender_Chosen.equals("Female")) {
                double result = (1.51 * Result) - (0.70 * Double.parseDouble(Age)) - (3.6 * 0) + 1.4;
                Fat.setText(String.valueOf(String.format("%.2f", result)) + "%");
                Fat.setTextColor(Color.parseColor("#673AB7"));
            } else if (Integer.parseInt(Age) >= 18 && Gender_Chosen.equals("Male")) {
                double AdultMale = (1.20 * Result) + (0.23 * Double.parseDouble(Age)) - (10.8 * 1) - 5.4;
                Fat.setText(String.valueOf(String.format("%.2f", AdultMale)) + "%");
                Fat.setTextColor(Color.BLUE);
            } else if (Integer.parseInt(Age) >= 18 && Gender_Chosen.equals("Female")) {
                double AdultFemale = (1.20 * Result) + (0.23 * Double.parseDouble(Age)) - (10.8 * 0) - 5.4;
                Fat.setText(String.valueOf(String.format("%.2f", AdultFemale)) + "%");
                Fat.setTextColor(Color.parseColor("#673AB7"));
            }


            if (Result <= 18.5 && Gender_Chosen.equals("Female")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Underweight category and means that you should consider a healthier lifestyle. You can start by trying the methods listed here. \n1)Eat more frequently. When you're underweight, you may feel full faster. Eat five to six smaller meals during the day rather than two or three large meals." +
                        "\n2)Choose nutrient-rich foods. As part of an overall healthy diet, choose whole-grain breads, pastas and cereals; fruits and vegetables; dairy products; lean protein sources; and nuts and seeds." + "\n3)Exercise. Exercise, especially strength training, can help you gain weight by building up your muscles. Exercise may also stimulate your appetite." );
                Background.setBackgroundResource(R.drawable.underweight_girl_result);
                BMI.setTextColor(Color.parseColor("#4DCCF3"));
                Gender.setTextColor(Color.parseColor("#673AB7"));
            } else if (Result <= 18.5 && Gender_Chosen.equals("Male")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Underweight category and means that you should consider a healthier lifestyle. You can start by trying the methods listed here. \n1)Eat more frequently. When you're underweight, you may feel full faster. Eat five to six smaller meals during the day rather than two or three large meals." +
                        "\n2)Choose nutrient-rich foods. As part of an overall healthy diet, choose whole-grain breads, pastas and cereals; fruits and vegetables; dairy products; lean protein sources; and nuts and seeds." + "\n3)Exercise. Exercise, especially strength training, can help you gain weight by building up your muscles. Exercise may also stimulate your appetite." );
                Background.setBackgroundResource(R.drawable.underweight_guy_result);
                BMI.setTextColor(Color.parseColor("#4DCCF3"));
                Gender.setTextColor(Color.BLUE);
            } else if (Result > 18.5 && Result <= 24.9 && Gender_Chosen.equals("Female")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis means that you are a normal/healthy weight for your height. \nPlease continue living a healthy lifestyle. You can never be too healthy :)" );
                Background.setBackgroundResource(R.drawable.healthy_girl_result);
                BMI.setTextColor(Color.parseColor("#34A449"));
                Gender.setTextColor(Color.parseColor("#673AB7"));
            } else if (Result > 18.5 && Result <= 24.9 && Gender_Chosen.equals("Male")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis means that you are a normal/healthy weight for your height. \nPlease continue living a healthy lifestyle. You can never be too healthy :)" );
                Background.setBackgroundResource(R.drawable.healthy_guy_result);
                BMI.setTextColor(Color.parseColor("#34A449"));
                Gender.setTextColor(Color.BLUE);
            } else if (Result >= 25 && Result <= 29.9 && Gender_Chosen.equals("Female")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Overweight category and means that you should consider a healthier lifestyle. You can start by trying the methods listed here. \n1)Watch what you eat. Try to eat more fruit, vegetables, nuts, and whole grains. While at home try to use vegetable-based oils rather than animal-based fats., and cut down your consumption of fatty and sugary foods." +
                        "\n2)Exercise. Try your best to exercise, even moderately, for at least 30 minutes a day.");
                Background.setBackgroundResource(R.drawable.overweight_girl_result);
                BMI.setTextColor(Color.parseColor("#F2C815"));
                Gender.setTextColor(Color.parseColor("#673AB7"));
            } else if (Result >= 25 && Result <= 29.9 && Gender_Chosen.equals("Male")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Overweight category and means that you should consider a healthier lifestyle. You can start by trying the methods listed here. \n1)Watch what you eat. Try to eat more fruit, vegetables, nuts, and whole grains. While at home try to use vegetable-based oils rather than animal-based fats., and cut down your consumption of fatty and sugary foods." +
                        "\n2)Exercise. Try your best to exercise, even moderately, for at least 30 minutes a day.");
                Background.setBackgroundResource(R.drawable.overweight_guy_result);
                BMI.setTextColor(Color.parseColor("#F2C815"));
                Gender.setTextColor(Color.BLUE);
            } else if (Result >= 30 && Gender_Chosen.equals("Female")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Obese category and means that it is critical that you consider a healthier lifestyle. You can start by trying the methods listed here. \n1)Watch what you eat. Try to eat more fruit, vegetables, nuts, and whole grains. While at home try to use vegetable-based oils rather than animal-based fats., and cut down your consumption of fatty and sugary foods." +
                        "\n2)Exercise. Try your best to exercise, even moderately, for at least 30 minutes a day.");
                Background.setBackgroundResource(R.drawable.obese_girl_result);
                BMI.setTextColor(Color.parseColor("#D72402"));
                Gender.setTextColor(Color.parseColor("#673AB7"));
            }  else if (Result >= 30 && Gender_Chosen.equals("Male")) {
                Advice.setText("Dear, " + Name + ". Your BMI was " + String.format("%.2f",Result) + ".\nThis puts you in the Obese category and means that it is critical that you consider a healthier lifestyle. You can start by trying the methods listed here. \n 1)Watch what you eat. Try to eat more fruit, vegetables, nuts, and whole grains. While at home try to use vegetable-based oils rather than animal-based fats., and cut down your consumption of fatty and sugary foods." +
                        "\n2)Exercise. Try your best to exercise, even moderately, for at least 30 minutes a day.");
                Background.setBackgroundResource(R.drawable.obese_result_test);
                BMI.setTextColor(Color.parseColor("#D72402"));
                Gender.setTextColor(Color.BLUE);
            }
        }



    }
    public void GoBack (View x) {
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.putExtra("Entered_Name", Name);
        startActivity(intent);
        finish();
    }
}
