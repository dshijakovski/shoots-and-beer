package com.example.shotsandbeer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shotsandbeer.Models.HighScore;
import com.example.shotsandbeer.R;

import java.util.ArrayList;

public class HighScoresAdapter extends RecyclerView.Adapter<HighScoresAdapter.ViewHolder> {

    Context ctx;
    ArrayList<HighScore> highScores;

    public HighScoresAdapter(Context ctx, ArrayList<HighScore> highScores) {
        this.ctx = ctx;
        this.highScores = highScores;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView timestamp;
        public TextView attempts;

        public ViewHolder(@NonNull View view) {
            super(view);

            timestamp = view.findViewById(R.id.highScoreTime);
            attempts = view.findViewById(R.id.highScoreAttempts);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HighScore highScore = highScores.get(position);

        holder.timestamp.setText(highScore.timestamp);
        holder.attempts.setText(highScore.attempts.toString());
    }

    @Override
    public int getItemCount() {
        return highScores.size();
    }

}
