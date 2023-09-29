package com.example.guessmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.guessmovie.Database.DatabaseHelper;
import com.example.guessmovie.Firebase.Usuarios;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton Loginbtn, feisbtn, twitterbtn;
    private EditText userTXT, passTXT;
    private DatabaseHelper databasehelper;

    private GoogleSignInClient client;
    FirebaseAuth auth;
    FirebaseDatabase database;
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
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client= GoogleSignIn.getClient(this,options);
        ImageButton btngugul = findViewById(R.id.gugulbtn);
        btngugul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = client.getSignInIntent();
                startActivityForResult(intent,1234);
            }
        });}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential (account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete (@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = auth.getCurrentUser();
                                    Usuarios users= new Usuarios();
                                    users.setUserId(user.getUid());
                                    users.setName(user.getDisplayName());
                                    users.setProfile(user.getPhotoUrl().toString());

                                    database.getReference().child("Users").child(user.getUid()).setValue(users);
                                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }}

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