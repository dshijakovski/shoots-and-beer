package com.example.shotsandbeer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shotsandbeer.Adapters.AttemptsAdapter;
import com.example.shotsandbeer.Models.Digit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewGame extends AppCompatActivity {

    RecyclerView attemptsRecyclerView;
    AttemptsAdapter adapter;
    ArrayList< ArrayList<Digit> > cells;

    TextView attemptsIndicator;
    EditText numberInput;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Initialize cells
        cells = new ArrayList<>();

        // Get views
        attemptsIndicator = findViewById(R.id.attemptsText);
        numberInput = findViewById(R.id.numberInput);
        Button submitButton = findViewById(R.id.submitButton);

        // Initialize RecyclerView data
        attemptsRecyclerView = findViewById(R.id.attemptsList);
        adapter = new AttemptsAdapter(this, cells);

        // Setup toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("New Game");
        }

        // Start game
        gameManager = new GameManager();
        startNewGame();

        // Create RecyclerView list
        attemptsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attemptsRecyclerView.setAdapter(adapter);

        // onClick listener for submit button
        submitButton.setOnClickListener(v -> {

            // Hide the keyboard if it's open
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            // Get guess from the edit text view
            String guess = numberInput.getText().toString();

            // Clear the input field
            numberInput.setText("");

            // Check invalid input
            if (guess.length() < 4) {
                numberInput.setError("Please enter 4 digits!");
                return;
            }

            updateCells(guess);

            gameManager.attempts++;
            attemptsIndicator.setText("Attempts: " + gameManager.attempts);

            checkEndGame(guess);
        });
    }

    private void startNewGame() {
        cells.clear();
        adapter.notifyDataSetChanged();
        gameManager.startNewGame();
        numberInput.setText("");
        attemptsIndicator.setText("Attempts: 0");
    }

    // Assigns colors and values for the new row of inputCells
    private void updateCells(String guess) {
        ArrayList<Digit> newInputCells = new ArrayList<>();

        // Get the corresponding colors for each digit
        int[] colors = gameManager.getDigitColors(guess);

        for (int i = 0; i < GameManager.DIGITS; i++) {
            newInputCells.add(new Digit(colors[i], guess.charAt(i)));
        }

        cells.add(newInputCells);
    }

    // Check for win or new attempt
    private void checkEndGame(String guess) {
        // Check if correct number
        if (guess.equals(gameManager.answer)) {
            showWinDialog();
            saveScore();
            return;
        }

        // Incorrect number, start a new attempt
        Toast.makeText(this, gameManager.getRandomFailedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void showWinDialog() {
        // Show winning dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You won! Congrats!");
        builder.setMessage("Attempts: " + gameManager.attempts);
        builder.setCancelable(true);

        builder.setPositiveButton("New Game", (dialog, id) -> {
            dialog.cancel();
            startNewGame();
        });

        builder.setNegativeButton("Main Menu", (dialog, id) -> {
            dialog.cancel();
            finish();
        });

        builder.create().show();
    }

    private void saveScore() {
        SharedPreferences sharedPreferences = getSharedPreferences(GameManager.HIGH_SCORES_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Get high scores set from shared preferences
        SimpleDateFormat dateFormat = new SimpleDateFormat(GameManager.TIMESTAMP_FORMAT);
        String timestamp = dateFormat.format(new Date());
        editor.putInt(timestamp, gameManager.attempts);
        editor.apply();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the toolbar back button click
        return true;
    }
}