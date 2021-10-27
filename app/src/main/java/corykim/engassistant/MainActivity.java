package corykim.engassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import corykim.engassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int x = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.corykim.setText("I am Cory hahaha");

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + 1;
                Toast.makeText(getApplicationContext(),"Button click count = " + x,Toast.LENGTH_SHORT).show();
                binding.minlee.setText("Button click count = " + x);
            }
        });
    }
}
