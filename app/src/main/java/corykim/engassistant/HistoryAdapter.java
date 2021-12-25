package corykim.engassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    List<SpeechHistory> data = new ArrayList<>();

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.englishText.setText(data.get(position).englishText);
        holder.koreanText.setText(data.get(position).koreanText);
        holder.time.setText(data.get(position).time);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void update(List<SpeechHistory> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView englishText;
        TextView koreanText;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            englishText = itemView.findViewById(R.id.englishText);
            koreanText = itemView.findViewById(R.id.translatedText);
        }
    }
}
