package corykim.engassistant;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface HistoryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SpeechHistory speechHistory);

    @Delete()
    void deleteItem(SpeechHistory speechHistory);

    @Query("SELECT * FROM history_table order by time desc")
    LiveData<List<SpeechHistory>> getNote();
}
