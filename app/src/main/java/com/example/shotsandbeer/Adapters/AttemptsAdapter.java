package com.example.shotsandbeer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shotsandbeer.GameManager;
import com.example.shotsandbeer.Models.Digit;
import com.example.shotsandbeer.R;

import java.util.ArrayList;

public class AttemptsAdapter extends RecyclerView.Adapter<AttemptsAdapter.ViewHolder> {

    Context ctx;
    ArrayList< ArrayList<Digit> > cells;

    public AttemptsAdapter(Context ctx, ArrayList<ArrayList<Digit>> cells) {
        this.ctx = ctx;
        this.cells = cells;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout cellLayout;
        public ArrayList<TextView> cells;

        public ViewHolder(View view) {
            super(view);

            cellLayout = view.findViewById(R.id.cellLayout);
            cells = new ArrayList<>(GameManager.DIGITS);
            cells.add(view.findViewById(R.id.cellText1));
            cells.add(view.findViewById(R.id.cellText2));
            cells.add(view.findViewById(R.id.cellText3));
            cells.add(view.findViewById(R.id.cellText4));
        }

    }

    @NonNull
    @Override
    public AttemptsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attempt_cell, parent, false);
        return new ViewHolder(view);
    }

    private void updateCell(TextView cellView, Digit inputCell) {
        cellView.setBackground(ctx.getDrawable(inputCell.color));
        cellView.setText(inputCell.value);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptsAdapter.ViewHolder holder, int position) {

        ArrayList<Digit> inputCells = cells.get(position);
        for (int i = 0; i < holder.cells.size(); i++) {
            TextView cellView = holder.cells.get(i);
            Digit inputCell = inputCells.get(i);
            updateCell(cellView, inputCell);
        }
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }
}
