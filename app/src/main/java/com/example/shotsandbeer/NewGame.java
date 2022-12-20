package com.example.shotsandbeer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewGame extends AppCompatActivity {

    RecyclerView attemptsRecyclerView;
    ArrayList< ArrayList<InputCell> > cells;

    TextView attemptsIndicator;
    TextView numberInput;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Get views
        attemptsIndicator = findViewById(R.id.attemptsText);
        numberInput = findViewById(R.id.numberInput);
        Button submitButton = findViewById(R.id.submitButton);
        attemptsRecyclerView = findViewById(R.id.attemptsList);

        // Display toolbar back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // Start game
        gameManager = new GameManager();
        startNewGame();

        // Initialize RecyclerView
        attemptsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attemptsRecyclerView.setAdapter(new AttemptsAdapter(this, cells));

        // onClick listener for submit button
        submitButton.setOnClickListener(v -> {

            // Hide the keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            // Get input from the edit text view
            String input = numberInput.getText().toString();
            // Clear the text
            numberInput.setText("");

            // Check invalid input
            if (input.length() < 4) {
                numberInput.setError("Please enter 4 digits!");
                return;
            }

            updateCells(input);

            gameManager.attempts++;
            attemptsIndicator.setText("Attempts: " + gameManager.attempts);

            checkEndGame(input);
        });
    }

    private void startNewGame() {
        cells = new ArrayList<>();
        gameManager.startNewGame();
        numberInput.setText("");
        attemptsIndicator.setText("Attempts: 0");
    }

    // Assigns colors and values for the new row of inputCells
    private void updateCells(String input) {
        char[] digits = input.toCharArray();
        ArrayList<InputCell> newInputCells = new ArrayList<>();

        for (int position = 0; position < digits.length; position++) {
            char digit = digits[position];
            int color = gameManager.getDigitColor(digit, position);

            newInputCells.add(new InputCell(color, digit));
        }

        cells.add(newInputCells);
    }

    // Check for win or new attempt
    private void checkEndGame(String input) {
        // Check if correct number
        if (input.equals(gameManager.targetNumber)) {
            // TODO: Show dialog here
            Toast.makeText(this, "You won! Congrats!", Toast.LENGTH_SHORT).show();
            // startNewGame();
            return;
        }

        // Incorrect number, start a new attempt
        // TODO: Create a toast with a few random messages
        Toast.makeText(this, "Almost there! Keep trying...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the toolbar back button click
        return true;
    }
}