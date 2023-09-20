package com.example.guessmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.guessmovie.Database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton Loginbtn, feisbtn, twitterbtn;
    private EditText userTXT, passTXT;
    private DatabaseHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasehelper = new DatabaseHelper(this);

        Loginbtn = findViewById(R.id.imageButton3);
        feisbtn = findViewById(R.id.imageButton);
        twitterbtn = findViewById(R.id.imageButton2);
        userTXT = findViewById(R.id.usertxt);
        passTXT = findViewById(R.id.passtxt);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Loginbtn.startAnimation(fadeInAnimation);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userTXT.getText().toString().trim();
                String password = passTXT.getText().toString().trim();

                if (!isValidUsername(username)) {
                    Toast.makeText(MainActivity.this, "Invalid Username no spaces between or special characters", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Insert a password", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(MainActivity.this, "Only allowed 6 digits and 1 numbers passwords", Toast.LENGTH_SHORT).show();
                } else {
                    // Verificar si el usuario ya existe en la base de datos
                    if (databasehelper.isUserExists(username)) {
                        Toast.makeText(MainActivity.this, "¡Welcome Back, " + username + " UwU", Toast.LENGTH_SHORT).show();
                    } else {
                        long result = databasehelper.insertUser(username, password);
                        if (result != -1) {
                            Toast.makeText(MainActivity.this, "New User has been registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Bad Entry in the DB", Toast.LENGTH_SHORT).show();
                        }
                    }

                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                    startActivity(intent);
                }
            }
        });

        feisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feisbtn.startAnimation(fadeInAnimation);
                mostrarUsuarios();
            }
        });

        twitterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterbtn.startAnimation(fadeInAnimation);
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
    private void mostrarUsuarios() {
        ArrayList<String> usuarios = databasehelper.getAllUsers();

        if (!usuarios.isEmpty()) {
            StringBuilder usuariosStr = new StringBuilder("Usuarios registrados:\n");

            for (String usuario : usuarios) {
                usuariosStr.append(usuario).append("\n");
            }

            Toast.makeText(MainActivity.this, usuariosStr.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "No hay usuarios registrados.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUsername(String username) {
        String patronUsername = "^[A-Za-z0-9]{5,}$";
        return username.matches(patronUsername);
    }

    private boolean isValidPassword(String password) {
        String patronPassword = "^(?=.*[0-9]).{6,}$";
        return password.matches(patronPassword);
    }
}