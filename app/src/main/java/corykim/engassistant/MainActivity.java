package corykim.engassistant;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;

import static java.time.MonthDay.now;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.translation.SpeechTranslationConfig;
import com.microsoft.cognitiveservices.speech.translation.TranslationRecognitionResult;
import com.microsoft.cognitiveservices.speech.translation.TranslationRecognizer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import corykim.engassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String key = "5deb918661344d0d9c684eeca23bf724";
    String location = "centralus";
    ActivityMainBinding binding;
    SpeechTranslationConfig speechConfig = SpeechTranslationConfig.fromSubscription(key, location);
    SpeechHistoryViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SpeechHistoryViewModel.class);
        speechConfig.setSpeechRecognitionLanguage("en-US");
        speechConfig.addTargetLanguage("ko");
        speechConfig.addTargetLanguage("zh-Hans");
        speechConfig.addTargetLanguage("ja");
        speechConfig.addTargetLanguage("es");

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
                String dateTime = SimpleDateFormat.getDateTimeInstance().format(new Date());
                viewModel.insert(new SpeechHistory(binding.englishText.getText().toString(), binding.translate.getText().toString(), dateTime));
                binding.englishText.setText("Press the button to translate.");
                binding.translate.setText("(Translation appears here.)");


            }
        });

        binding.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String[] languageChoices = new String[] {"Korean", "Chinese", "Japanese", "Spanish"};
                builder.setTitle("Translate to:")
                    .setItems(languageChoices, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            binding.language.setText(languageChoices[which]);

                        }
                    });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }



    public void fromMic(SpeechTranslationConfig speechConfig) throws Exception {
        try (TranslationRecognizer recognizer = new TranslationRecognizer(speechConfig)) {
            TranslationRecognitionResult result = recognizer.recognizeOnceAsync().get();
            if (result.getReason() == ResultReason.TranslatedSpeech) {
                binding.englishText.setText(result.getText());
                for (Map.Entry<String, String> pair : result.getTranslations().entrySet()) {
                    String targetLanguage = pair.getKey();
                    String translatedLanguage = pair.getValue();
                    if (binding.language.getText().toString().equals("Korean")) {
                        if (targetLanguage.equals("ko")) {
                            binding.translate.setText(translatedLanguage);
                        }
                    }
                    if (binding.language.getText().toString().equals("Spanish")){
                        if (targetLanguage.equals("es")) {
                            binding.translate.setText(translatedLanguage);
                        }
                    }
                    if (binding.language.getText().toString().equals("Chinese")){
                        if (targetLanguage.equals("zh-Hans")) {
                            binding.translate.setText(translatedLanguage);
                        }
                    }
                    if (binding.language.getText().toString().equals("Japanese")){
                        if (targetLanguage.equals("ja")) {
                            binding.translate.setText(translatedLanguage);
                        }
                    }
                }
            }
        }
    }
}
