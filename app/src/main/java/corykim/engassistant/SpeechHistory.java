package corykim.engassistant;

public class SpeechHistory {
    public String englishText;
    public String koreanText;
    public String time;
    public String elapsed;

    public SpeechHistory(String englishText, String koreanText, String time, String elapsed) {
        this.englishText = englishText;
        this.koreanText =koreanText;
        this.time = time;
        this.elapsed = elapsed;
    }
}
