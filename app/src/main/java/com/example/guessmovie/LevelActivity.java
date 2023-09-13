package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity {
TextView nameLevelTextView;
    TextView statusLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);




        ListItemM itemM = (ListItemM) getIntent().getSerializableExtra("ListItemM");
        nameLevelTextView = findViewById(R.id.LevelNameTextView);
        statusLevelTextView = findViewById(R.id.LevelStatusTextView);

        nameLevelTextView.setText(itemM.getName());

        statusLevelTextView.setText(itemM.getStatus());
    }
}