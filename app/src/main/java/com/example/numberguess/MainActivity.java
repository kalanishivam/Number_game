package com.example.numberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RadioButton twoDig , threeDig, fourDig;
    Button start;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoDig = findViewById(R.id.radioButtonTwo);
        threeDig  =findViewById(R.id.radioButtonThree);
        fourDig = findViewById(R.id.radioButtonFour);

        start = findViewById(R.id.buttonStart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , GameActivity.class);
                if(!twoDig.isChecked() && !threeDig.isChecked() && !fourDig.isChecked()){
                    Snackbar.make(view , "Select number of digits" , Snackbar.LENGTH_LONG).show();
                }
                else{
                    if(twoDig.isChecked()){
                        intent.putExtra("two" , true);
                    }
                    if(threeDig.isChecked()){
                        intent.putExtra("three" , true);
                    }
                    if(fourDig.isChecked()){
                        intent.putExtra("four" , true);
                    }

                    startActivity(intent);
//                    finish();
                }
            }
        });

    }
}