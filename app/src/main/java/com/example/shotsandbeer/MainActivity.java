package com.example.shotsandbeer;
// REVIEW: Package name is “com.example”, changing this later is pretty painful, so usually we
// have it as com.testdevlab, but in case of this junior task, it is best to put your name there:
// com.danielshijakovski.shotsandbeer for example
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
