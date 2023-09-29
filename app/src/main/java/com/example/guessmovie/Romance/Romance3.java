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

public class Romance3 extends AppCompatActivity {
    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndex = 0;

    private String[] hints = {
            " A vibrant journey through the afterlife in animation",
            " An immortal bet shapes an extraordinary love story",
            " Music and tradition guide the hero's adventure in this visually stunning tale "
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance3);

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

                if (answer.equals("book of life")) {
                    Toast.makeText(Romance3.this, "CONGRATULATIONS LEVEL 3 COMPLETED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Romance3.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Romance3.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}