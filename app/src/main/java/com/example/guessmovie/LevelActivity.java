package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.guessmovie.MovieGames.Action1;
import com.example.guessmovie.MovieGames.Action2;
import com.example.guessmovie.MovieGames.Action3;

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

        String[] levelNames = {"Level 1", "Level 2", "Level 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, levelNames);

        ListView listView = findViewById(R.id.actionlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent nivel1Intent = new Intent(LevelActivity.this, Action1.class);
                        startActivity(nivel1Intent);
                        break;
                    case 1:
                        Intent nivel2Intent = new Intent(LevelActivity.this, Action2.class);
                        startActivity(nivel2Intent);
                        break;
                    case 2:
                        Intent nivel3Intent = new Intent(LevelActivity.this, Action3.class);
                        startActivity(nivel3Intent);
                        break;
                    default:

                }
            }
        });
    }
    }









