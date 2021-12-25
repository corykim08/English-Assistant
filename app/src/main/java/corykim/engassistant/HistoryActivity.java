package corykim.engassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import corykim.engassistant.databinding.ActivityHistoryBinding;
import corykim.engassistant.databinding.ActivityMainBinding;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding binding;
    SpeechHistoryViewModel viewModel;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History");
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HistoryAdapter adapter = new HistoryAdapter();
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerview.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.divider));
        binding.recyclerview.addItemDecoration(dividerItemDecoration);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SpeechHistoryViewModel.class);
        viewModel.speechHistoryLiveData.observe(this, speechHistories -> {
            adapter.update(speechHistories);
        });

//        List<SpeechHistory> temporaryData = new ArrayList<>();
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 1 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 2 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 3 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 4 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 5 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 6 ", "11/11/2021"));
//        temporaryData.add(new SpeechHistory("Hi, my name is Cory", "번역 7 ", "11/11/2021"));
//        adapter.update(temporaryData);
    }
}

