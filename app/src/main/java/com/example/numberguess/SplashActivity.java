package com.example.numberguess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    ConstraintLayout layout;

    Animation animationImage , animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        animationImage = AnimationUtils.loadAnimation(this , R.anim.image_anima);
        animationText = AnimationUtils.loadAnimation(this , R.anim.text_animation);
        layout  =findViewById(R.id.Constraint);

        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);

        new CountDownTimer(5000 ,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this , MainActivity.class));
                finish();
            }
        }.start();
    }
}