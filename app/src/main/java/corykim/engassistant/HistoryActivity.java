package corykim.engassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import corykim.engassistant.databinding.ActivityHistoryBinding;
import corykim.engassistant.databinding.ActivityMainBinding;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History");
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HistoryAdapter adapter = new HistoryAdapter();
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<SpeechHistory> temporaryData = new ArrayList<>();
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 1 ", "11/11/2021", "3 days"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 2 ", "11/11/2021", "5 days"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 3 ", "11/11/2021", "5 days"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 4 ", "11/11/2021", "6 days"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 5 ", "11/11/2021", "8 days"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 6 ", "11/11/2021", "1 month"));
        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 7 ", "11/11/2021", "2 month"));
        adapter.update(temporaryData);
    }
}

