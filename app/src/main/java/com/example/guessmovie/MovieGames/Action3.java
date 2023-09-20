package com.example.guessmovie.MovieGames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmovie.R;

public class Action3 extends AppCompatActivity {

    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndex = 0;

    private String[] hints = {
            "There's nothing love can't do",
            "Family is all that matters",
            "Never give up"
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
                String answer = answerEditText.getText().toString();

                if (answer.equals("Fast and furious")) {
                    Toast.makeText(Action3.this, "CONGRATULATIONS LEVEL 3 COMPLETED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Action3.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}