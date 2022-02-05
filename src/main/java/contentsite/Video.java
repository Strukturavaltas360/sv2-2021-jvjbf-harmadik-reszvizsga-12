package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Video implements Content{

    private String title;

    private int length;

    private List<User> users = new ArrayList<>();

    public Video(String title, int length) {
        this.title = title;
        this.length = length;
    }

    @Override
    public boolean isPremiumContent() {
        if (length >15) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users);
    }

    @Override
    public void click(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(title, video.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
