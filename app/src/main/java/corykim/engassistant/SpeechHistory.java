package corykim.engassistant;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_table")
public class SpeechHistory {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    @ColumnInfo(name = "englishText")
    public String englishText;
    @NonNull
    @ColumnInfo(name = "koreanText")
    public String koreanText;
    @NonNull
    @ColumnInfo(name = "time")
    public String time;

    public SpeechHistory(@NonNull String english, @NonNull String korean, @NonNull String time) {
        this.englishText = english;
        this.koreanText = korean;
        this.time = time;
    }

    public SpeechHistory() {

    }
}
