package com.example.appcinema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<String> scheduleList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String schedule);
    }

    public ScheduleAdapter(List<String> scheduleList, OnItemClickListener listener) {
        this.scheduleList = scheduleList;
        this.listener = listener;
    }


    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        String schedule = scheduleList.get(position);
        holder.scheduleTextView.setText(schedule);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(schedule));
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {

        TextView scheduleTextView;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            scheduleTextView = itemView.findViewById(R.id.schedule_text);
        }
    }
}

