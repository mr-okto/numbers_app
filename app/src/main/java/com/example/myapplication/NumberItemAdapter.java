package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumberItemAdapter extends RecyclerView.Adapter<NumberItemAdapter.NumberViewHolder> {

    private final List<NumberItem> numbersList;
    private NumberClicker listener;

    NumberItemAdapter(List<NumberItem> items, NumberClicker _listener) {
        numbersList = items;
        listener = _listener;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_in_list, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, final int position) {
        final NumberItem number = numbersList.get(position);
        holder.view.setText(String.valueOf(number.num));
        holder.view.setTextColor(number.color);
        holder.view.setOnClickListener(v -> {
            if (listener != null) {
                listener.onNumberClicked(number);

            }
        });
    }

    @Override
    public int getItemCount() {
        return numbersList.size();
    }

    static class NumberViewHolder extends RecyclerView.ViewHolder {
        final TextView view;

        NumberViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.number_small);

        }

    }

    void clearRefs() {
        listener = null;
    }

    public interface NumberClicker {
        void onNumberClicked(NumberItem item);
    }
}