package com.example.guessmovie.Romance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmovie.Menu;
import com.example.guessmovie.R;

public class Romance2 extends AppCompatActivity {

    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndexR = 0;

    private String[] hintsR = {
            "     Cruiser",
            "     Love Story",
            "     Iceberg "
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance2);

        hintTextView = findViewById(R.id.hint);
        hintButton = findViewById(R.id.hintbtn);
        answerButton = findViewById(R.id.Answerbtn);
        answerEditText = findViewById(R.id.Answer);

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintTextView.setText(hintsR[hintIndexR]);
                hintIndexR = (hintIndexR + 1) % hintsR.length;
            }
        });

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerEditText.getText().toString().toLowerCase();

                if (answer.equals("titanic")) {
                    Toast.makeText(Romance2.this, "CONGRATULATIONS LEVEL 2 COMPLETED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Romance2.this, RomanceMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Romance2.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }}