package corykim.engassistant;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SpeechHistory.class}, version = 1, exportSchema = false)
public abstract class HistoryRoomDatabase extends RoomDatabase {

    public abstract HistoryDao NoteDao();

    private static volatile HistoryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HistoryRoomDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (HistoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getInstance(),
                            HistoryRoomDatabase.class, "history_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}