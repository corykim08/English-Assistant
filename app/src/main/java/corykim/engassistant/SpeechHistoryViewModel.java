package corykim.engassistant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SpeechHistoryViewModel extends ViewModel {

    private HistoryRepository repository = new HistoryRepository();
    public LiveData<List<SpeechHistory>> speechHistoryLiveData = repository.speechHistoryLiveData;

    public void insert(SpeechHistory speechHistory) { repository.insert(speechHistory); }
}
