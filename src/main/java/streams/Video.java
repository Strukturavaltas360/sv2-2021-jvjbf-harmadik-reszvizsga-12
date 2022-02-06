package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private String title;
    private int length;
    private LocalDate uploadDate;
    private List<String> hashTags = new ArrayList<>();

    public Video(String title, int length, LocalDate uploadDate, List<String> hashtags) {
        this.title = title;
        this.length = length;
        this.uploadDate = uploadDate;
        this.hashTags = hashtags;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public List<String> getHashTags() {
        return new ArrayList<>(hashTags);
    }
}
