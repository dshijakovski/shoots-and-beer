package com.example.shotsandbeer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.shotsandbeer.Adapters.HighScoresAdapter;
import com.example.shotsandbeer.Models.HighScore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class HighScores extends AppCompatActivity {

    RecyclerView highScoresRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        // Setup the toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("High Scores");
        }

        // Get the high scores from shared prefs
        SharedPreferences sharedPreferences = getSharedPreferences(GameManager.HIGH_SCORES_PREFS, Context.MODE_PRIVATE);
        Map<String, ?> highScores = sharedPreferences.getAll();

        ArrayList<HighScore> highScoresList = new ArrayList<>();

        // Convert the map to a list of high scores
        for (Map.Entry<String, ?> entry: highScores.entrySet()) {
            String timestamp = entry.getKey();
            Integer attempts = (Integer) entry.getValue();
            highScoresList.add(new HighScore(timestamp, attempts));
        }

        // Sort the highScoresList by attempts
        Comparator<HighScore> attemptsComparator = Comparator.comparing(highScore -> highScore.attempts);
        highScoresList.sort(attemptsComparator);

        // Initialize the RecyclerView
        highScoresRecyclerView = findViewById(R.id.highScoresList);
        highScoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        highScoresRecyclerView.setAdapter(new HighScoresAdapter(this, highScoresList));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the toolbar back button click

        return true;
    }
}