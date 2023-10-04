package com.example.guessmovie.YourList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.guessmovie.Database.DatabaseHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmovie.Database.MovieData;
import com.example.guessmovie.Menu;
import com.example.guessmovie.R;

import org.json.JSONException;
import org.json.JSONObject;

public class YourMovie3 extends AppCompatActivity {
    private TextView hintTextView;
    private Button hintButton;
    private Button answerButton;
    private Button deleteBtn;
    private EditText answerEditText;
    private TextView showlabel;
    private int hintIndex = 0;
    private WebView webview;

    private String[] hints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_movie3);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        int levelSelected = 3;
        MovieData movieData = databaseHelper.getMovieData(levelSelected);


        if (movieData != null) {
            hints = new String[]{
                    movieData.getHint1(),
                    movieData.getHint2(),
                    movieData.getHint3()
            };
            //Animation ScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
            deleteBtn= findViewById(R.id.deletebtn);
            hintTextView = findViewById(R.id.hint);
            hintButton = findViewById(R.id.hintbtn);
            answerButton = findViewById(R.id.Answerbtn);
            answerEditText = findViewById(R.id.Answer);
            showlabel = findViewById(R.id.showlabel);
            webview = findViewById(R.id.webView);
            webview.setVisibility(View.GONE);
            hintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //hintButton.startAnimation(ScaleAnimation);
                    hintTextView.setText(hints[hintIndex]);
                    hintIndex = (hintIndex + 1) % hints.length;
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(YourMovie3.this);
                    builder.setTitle("DELETE");
                    builder.setMessage("Are you sure to delete this movie? :0\"");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseHelper dbHelper = new DatabaseHelper(YourMovie3.this);
                            dbHelper.deleteMovieByLevel(3);
                            Intent nivel1Intent = new Intent(YourMovie3.this,YourMenu.class);
                            startActivity(nivel1Intent);
                            Toast.makeText(YourMovie3.this, "Movie was deleted", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //answerButton.startAnimation(ScaleAnimation);
                    String answer = answerEditText.getText().toString().toLowerCase();

                    if (answer.equals(movieData.getTitle().toLowerCase())) {
                        Toast.makeText(YourMovie3.this, "CONGRATULATIONS LEVEL 2 COMPLETED", Toast.LENGTH_SHORT).show();
                        getDataFromDatabase();
                        webview.setVisibility(View.VISIBLE);
                        hideKeyboard();
                    } else {
                        Toast.makeText(YourMovie3.this, "Continue Trying", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}

    private void getDataFromDatabase() {
        String apiKey = "dfcaff24";
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        int levelSelected = 3;
        MovieData movieData = databaseHelper.getMovieData(levelSelected);

        if (movieData != null) {
            String title = movieData.getTitle();

            String url = "https://www.omdbapi.com/?t=" + title + "&apikey=" + apiKey;

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("Respuesta JSON", response.toString());
                                String plot = response.getString("Plot");
                                showlabel.setText(plot);
                                String posterUrl = response.getString("Poster");
                                String htmlData = "<html><head><style>" +
                                        "body { display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }" +
                                        "img { max-width: 100%; max-height: 100%; }" +
                                        "</style></head><body>" +
                                        "<img src=\"" + posterUrl + "\" alt=\"Poster de la película\">" +
                                        "</body></html>";

                                webview.getSettings().setJavaScriptEnabled(true);
                                webview.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                showlabel.setText("There was an error obtaining the plot :c");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley Error", "There was an error :c", error);
                            Toast.makeText(YourMovie3.this, "Error gathering all data: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            requestQueue.add(request);
        } else {
            Toast.makeText(YourMovie3.this, "Movie not found in the database :c", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(answerEditText.getWindowToken(), 0);
    }



}
