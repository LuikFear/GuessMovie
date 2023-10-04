package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.guessmovie.Romance.RomanceMenu;
import com.example.guessmovie.YourList.YourMenu;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    private List<ListItemM> items;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ImageButton MenuBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    MenuBtn = findViewById(R.id.profilebtn);
        MenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

init();

    }


    public void init() {
        items = new ArrayList<>();
       items.add(new ListItemM("775447","Action Movies", "Action Movies Only Play now","OwO"));
        items.add(new ListItemM("775447","Romance Movies", "Romance Movies Only Play now","<3"));
        items.add(new ListItemM("775447","Your List", "Personalize your list here",":D"));

        ListAdapter listAdapter = new ListAdapter(items, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListItemM itemM) {
              moveTolevel(itemM);
            }
        });
        RecyclerView recyclerView1 = findViewById(R.id.RVMenu);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(listAdapter);
    }

    public class ActionActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);

            Intent intent = getIntent();
            if (intent != null) {
                ListItemM itemM = intent.getParcelableExtra("ListItemM");
            }
        }
    }

    public void moveTolevel(ListItemM itemM) {
        Intent intent;

        if ("Action Movies".equals(itemM.getName())) {
            intent = new Intent(this, LevelActivity.class);
        } else if ("Romance Movies".equals(itemM.getName())) {
            intent = new Intent(this, RomanceMenu.class);
        } else if ("Your List".equals(itemM.getName())) {
            intent = new Intent(this, YourMenu.class);
        } else {
            return;
        }

        intent.putExtra("ListItemM", itemM);
        startActivity(intent);
    }


}