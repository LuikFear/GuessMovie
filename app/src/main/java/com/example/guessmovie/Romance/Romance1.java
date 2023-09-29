package com.example.guessmovie.Romance;

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
import com.example.guessmovie.MovieGames.Action1;
import com.example.guessmovie.R;

public class Romance1 extends AppCompatActivity {

    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndex = 0;

    private String[] hints = {
            "   Magic transforms for a royal dance",
            "   A lost shoe sparks a love story",
            "   Kindness triumphs in this classic"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance1);

        hintTextView = findViewById(R.id.hint);
        hintButton = findViewById(R.id.hintbtn);
        answerButton = findViewById(R.id.AnswerbtnRomance);
        answerEditText = findViewById(R.id.AnswerRomance);

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

                if (answer.equals("cinderella")) {
                    Toast.makeText(Romance1.this, "CONGRATULATIONS LEVEL 1 COMPLETED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Romance1.this, RomanceMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Romance1.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}