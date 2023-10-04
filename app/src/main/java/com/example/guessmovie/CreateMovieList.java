package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guessmovie.Database.DatabaseHelper;
import com.example.guessmovie.MovieGames.Action1;
import com.example.guessmovie.YourList.YourMenu;
import com.example.guessmovie.YourList.YourMovie1;

public class CreateMovieList extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
Button addbtn;
EditText titletxt, destxt, hint1,hint2,hint3,lvselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie_list);
        addbtn=findViewById(R.id.addButton);
        titletxt=findViewById(R.id.Title);
        //destxt=findViewById(R.id.Des);
        hint1=findViewById(R.id.hint1);
        hint2=findViewById(R.id.hint2);
        hint3=findViewById(R.id.hint3);
        lvselect=findViewById(R.id.LvSelect);
        databaseHelper = new DatabaseHelper(this);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titletxt.getText().toString();
                String description = "des";
                String hint1Value = hint1.getText().toString();
                String hint2Value = hint2.getText().toString();
                String hint3Value = hint3.getText().toString();
                String lvselectValue = lvselect.getText().toString();
                int lvselectIntValue = 0;

                try {
                    lvselectIntValue = Integer.parseInt(lvselectValue);

                    if (lvselectIntValue < 1 || lvselectIntValue > 3) {
                        Toast.makeText(CreateMovieList.this, "Select a level Between 1 and 3", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(CreateMovieList.this, "Only numbers allowed  OwO", Toast.LENGTH_SHORT).show();
                    return;
                }


                long result = databaseHelper.insertMovie(title, description, hint1Value, hint2Value, hint3Value, lvselectIntValue);

                if (result != -1) {
                    Toast.makeText(CreateMovieList.this, "Your movie was added c:", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), YourMenu.class);
                    intent.putExtra("title", title);
                    intent.putExtra("hints", new String[]{hint1Value, hint2Value, hint3Value});
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateMovieList.this, "There was an error while adding ur movie :c", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }

}