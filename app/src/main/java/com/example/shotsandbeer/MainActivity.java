package com.example.shotsandbeer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newGameButton = findViewById(R.id.newGameBtn);
        newGameButton.setOnClickListener(v -> {
            startActivity(new Intent(this, NewGame.class));
        });

        Button highScoresButton = findViewById(R.id.highScoresBtn);
        highScoresButton.setOnClickListener(v -> {
            startActivity(new Intent(this, HighScores.class));
        });
    }
}