package emailservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String emailAddress;
    List<Email> incoming = new ArrayList<>();
    List<Email> sent = new ArrayList<>();
    private boolean hasSpamFilter;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getEmail(Email email) {
        if (hasSpamFilter && email.getSubject().contains("spam")) {
            throw new IllegalStateException("Be careful, tis is a spam!");
        }
        if (email.isImportant()) {
            incoming.add(0, email);
        } else {
            incoming.add(email);
        }
    }

    public void sendEmail(String subject, String content, User to) {
        Email normal = new Normal(this, to, subject, content);
        sent.add(normal);
        to.getEmail(normal);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    public void spamFilterChange() {
        if (hasSpamFilter == true) {
            hasSpamFilter = false;
        } else {
            hasSpamFilter = true;
        }
    }

    public List<Email> getIncoming() {
        return new ArrayList<>(incoming);
    }

    public List<Email> getSent() {
        return new ArrayList<>(sent);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean hasSpamFilter() {
        return hasSpamFilter;
    }
}

