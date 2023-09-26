package com.example.guessmovie.YourList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmovie.Database.DatabaseHelper;
import com.example.guessmovie.Database.MovieData;
import com.example.guessmovie.LevelActivity;
import com.example.guessmovie.Menu;
import com.example.guessmovie.MovieGames.Action1;
import com.example.guessmovie.R;

public class YourMovie2 extends AppCompatActivity {

    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private EditText answerEditText;
    private int hintIndex = 0;

    private String[] hints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_movie2);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        int levelSelected =2;
        MovieData movieData = databaseHelper.getMovieData(levelSelected);


        if (movieData != null) {
            // Configurar las pistas y la respuesta correcta con los datos de la película
            hints = new String[]{
                    movieData.getHint1(),
                    movieData.getHint2(),
                    movieData.getHint3()
            };
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

                    if (answer.equals(movieData.getTitle().toLowerCase())) {
                        Toast.makeText(YourMovie2.this, "CONGRATULATIONS YOUR LEVEL 2 COMPLETED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(YourMovie2.this, YourMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(YourMovie2.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {

            Toast.makeText(YourMovie2.this, "Movie not found in the database", Toast.LENGTH_SHORT).show();
        }





        }
}