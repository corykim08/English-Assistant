package corykim.engassistant;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import com.microsoft.cognitiveservices.speech.translation.SpeechTranslationConfig;
import com.microsoft.cognitiveservices.speech.translation.TranslationRecognitionResult;
import com.microsoft.cognitiveservices.speech.translation.TranslationRecognizer;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import corykim.engassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String key = "5deb918661344d0d9c684eeca23bf724";
    String location = "centralus";
    ActivityMainBinding binding;
    SpeechTranslationConfig speechConfig = SpeechTranslationConfig.fromSubscription(key, location);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        speechConfig.setSpeechRecognitionLanguage("en-US");
        speechConfig.addTargetLanguage("ko");

        // Initialize SpeechSDK and request required permissions.
        try {
            // a unique number within the application to allow
            // correlating permission request responses with the request.
            int permissionRequestId = 5;

            // Request permissions needed for speech recognition
            ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, INTERNET, READ_EXTERNAL_STORAGE}, permissionRequestId);
        } catch (Exception ex) {
            Log.e("SpeechSDK", "could not init sdk, " + ex.toString());
        }

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Speak now" ,Toast.LENGTH_SHORT).show();
                binding.save.setEnabled(true);
                try {
                    fromMic(speechConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));

            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.save.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Sentence saved." ,Toast.LENGTH_SHORT).show();




            }
        });
    }



    public void fromMic(SpeechTranslationConfig speechConfig) throws Exception {
        try (TranslationRecognizer recognizer = new TranslationRecognizer(speechConfig)) {
            TranslationRecognitionResult result = recognizer.recognizeOnceAsync().get();
            if (result.getReason() == ResultReason.TranslatedSpeech) {
                binding.corykim.setText(result.getText());
                for (Map.Entry<String, String> pair : result.getTranslations().entrySet()) {
                    binding.translate.setText(pair.getValue());
                }
            }
        }
    }
}
