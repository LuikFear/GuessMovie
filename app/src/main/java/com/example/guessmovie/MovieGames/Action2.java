package com.example.guessmovie.MovieGames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmovie.LevelActivity;
import com.example.guessmovie.Menu;
import com.example.guessmovie.R;

public class Action2 extends AppCompatActivity {

    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndex = 0;

    private String[] hints = {
            "Chimichangas",
            "I am touching myself tonight",
            "let's go give it to ya♫\t "
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action1);

        hintTextView = findViewById(R.id.hint);
        hintButton = findViewById(R.id.hintbtn);
        answerButton = findViewById(R.id.Answerbtn);
        answerEditText = findViewById(R.id.Answer);

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintTextView.setText(hints[hintIndex]);
                hintIndex = (hintIndex + 1) % hints.length;
            }
        });

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerEditText.getText().toString().toLowerCase();

                if (answer.equals("deadpool")) {
                    Toast.makeText(Action2.this, "CONGRATULATIONS LEVEL 2 COMPLETED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Action2.this, LevelActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Action2.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}