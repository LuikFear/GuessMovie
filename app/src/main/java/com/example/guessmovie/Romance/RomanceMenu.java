package com.example.guessmovie.Romance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guessmovie.LevelActivity;
import com.example.guessmovie.ListItemM;
import com.example.guessmovie.MovieGames.Action1;
import com.example.guessmovie.MovieGames.Action2;
import com.example.guessmovie.MovieGames.Action3;
import com.example.guessmovie.R;

public class RomanceMenu extends AppCompatActivity {

    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        String[] levelNames = {"Level 1", "Level 2", "Level 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, levelNames);
        title = findViewById(R.id.LevelNameTextView);
        title.setText("Romance Movies♥");
        ListView listView = findViewById(R.id.actionlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent nivel1Intent = new Intent(RomanceMenu.this, Romance1.class);
                        startActivity(nivel1Intent);
                        break;
                    case 1:
                        Intent nivel2Intent = new Intent(RomanceMenu.this, Romance2.class);
                        startActivity(nivel2Intent);
                        break;
                    case 2:
                        Intent nivel3Intent = new Intent(RomanceMenu.this, Action3.class);
                        startActivity(nivel3Intent);
                        break;
                    default:

                }
            }
        });
    }
}