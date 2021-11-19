package corykim.engassistant;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HistoryRepository {

    private HistoryDao historyDao;
    public LiveData<List<SpeechHistory>> speechHistoryLiveData;

    HistoryRepository() {
        HistoryRoomDatabase db = HistoryRoomDatabase.getDatabase();
        historyDao = db.NoteDao();
        speechHistoryLiveData = historyDao.getNote();
    }

    void insert(SpeechHistory speechHistory) {
        HistoryRoomDatabase.databaseWriteExecutor.execute(() -> {
            historyDao.insert(speechHistory);
        });
    }
}