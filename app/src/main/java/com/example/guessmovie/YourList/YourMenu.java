package com.example.guessmovie.YourList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.guessmovie.CreateMovieList;
import com.example.guessmovie.Menu;
import com.example.guessmovie.MovieGames.Action3;
import com.example.guessmovie.R;
import com.example.guessmovie.Romance.Romance1;
import com.example.guessmovie.Romance.Romance2;
import com.example.guessmovie.Romance.RomanceMenu;

public class YourMenu extends AppCompatActivity {

    ImageButton plusbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_menu);

        String[] levelNames = {"Your level 1", "Your level 2", "Your level 3"};

        plusbtn = findViewById(R.id.plusbtn);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, levelNames);
        ListView listView = findViewById(R.id.YourListL);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent nivel1Intent = new Intent(YourMenu.this, YourMovie1.class);
                        startActivity(nivel1Intent);
                        break;
                    case 1:
                        Intent nivel2Intent = new Intent(YourMenu.this, YourMovie2.class);
                        startActivity(nivel2Intent);
                        break;
                    case 2:
                        Intent nivel3Intent = new Intent(YourMenu.this, Action3.class);
                        startActivity(nivel3Intent);
                        break;
                    default:

                }
            }
        });

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusbtn.startAnimation(fadeInAnimation);
                Intent intent = new Intent(getApplicationContext(), CreateMovieList.class);
                startActivity(intent);
            }
        });
    }
}