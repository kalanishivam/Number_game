package com.example.numberguess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textView1 , textView2 , textViewGuess;
    EditText editTextGuess;
    Button buttonConfirm;

    boolean twoDigits , threeDigits , fourDigits;

    Random random = new Random();
    int num;
    int remainingLife  = 10;

    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttempt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView1 = findViewById(R.id.textView1);
        textView2  =findViewById(R.id.textViewRG);
        textViewGuess = findViewById(R.id.textViewGUESS);
        editTextGuess = findViewById(R.id.editTextText);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        twoDigits = getIntent().getBooleanExtra("two" , false);
        threeDigits = getIntent().getBooleanExtra("three" , false);
        fourDigits = getIntent().getBooleanExtra("four" , false);

        if(twoDigits){
            num = random.nextInt(90) + 10;
        }

        if(threeDigits){
            num = random.nextInt(900) + 100;
        }

        if(fourDigits){
            num = random.nextInt(9000) + 1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = editTextGuess.getText().toString();
                if(guess.equals("")){
                    Toast.makeText(GameActivity.this , "Enter your guess" , Toast.LENGTH_LONG).show();
                }
                else{
                    textView1.setVisibility(View.VISIBLE);
                    textViewGuess.setVisibility(View.VISIBLE);

                    remainingLife--;
                    userAttempt++;

                    int userGuess = Integer.parseInt(guess);
                    guessList.add(userGuess);

                    textView1.setText("Your last guess: " + guess);
                    textView2.setText("Number of lives remaining: " + remainingLife);

                    if(num == userGuess){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations , you got it right in " + userAttempt + " attempts.\n Your guesses: " + guessList + "\nWould you like to play again");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this , MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();


                    }
                    if(num < userGuess){
                        textViewGuess.setText("Decrease your Guess");
                    }
                    if(num >userGuess){
                        textViewGuess.setText("Increase your Guess");
                    }
                    if(remainingLife == 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setCancelable(false);
                        builder.setMessage("YOU LOST. The correct answer was: " + num + "\nWould you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this , MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();

                    }

                    editTextGuess.setText("");
                }
            }
        });


    }
}