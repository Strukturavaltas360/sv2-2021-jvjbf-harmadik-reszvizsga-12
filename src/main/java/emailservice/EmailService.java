package emailservice;

import java.util.HashSet;

import java.util.Set;

public class EmailService {

    Set<User> users = new HashSet<>();

    public void registerUser(String emailAddress) {
        String newEmailAddress = emailAddress.toLowerCase();
        if (!emailAddress.equals(newEmailAddress)) {
            throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
        }

        int indexOfAt = newEmailAddress.indexOf("@");
        int indexOfDot = newEmailAddress.indexOf(".");
        if (indexOfAt > 0 && indexOfDot > indexOfAt + 1) {
         if (!users.add(new User(newEmailAddress))) {
             throw new IllegalArgumentException("Email address is already taken!");
         }
        } else {
            throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
        }
    }

    public void sendEmail(String from, String to, String subject, String content) {
        User fromUser = null;
        User toUser = null;
        for (User actual : users) {
            if (actual.getEmailAddress().equals(from)) {
                fromUser = actual;
            }
            if (actual.getEmailAddress().equals(to)) {
                toUser = actual;
            }
        }
        fromUser.sendEmail(subject, content, toUser);
    }

    public void sendSpam(String subject, String content) {
        for (User actual : users) {
            actual.getEmail(new Spam(actual, subject, content));
        }
    }

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }
}
