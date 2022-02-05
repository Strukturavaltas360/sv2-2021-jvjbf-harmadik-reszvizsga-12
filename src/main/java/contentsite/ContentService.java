package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {

    Set<Content> allContent = new HashSet<>();
    Set<User> allUsers = new HashSet<>();

    public void registerUser(String name, String password) {
        if (!allUsers.add(new User(name, password))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
    }

    public void addContent(Content content) {
        if (!allContent.add(content)) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
    }

    public void logIn(String username, String password) {

        User tempUser = new User(username, password);
        if (!allUsers.contains(tempUser)) {
            throw new IllegalArgumentException("Username is wrong!");
        }

        int realPassword = (username + password).hashCode();
        for (User actual : allUsers) {
            if (actual.getPassword() == realPassword) {
                actual.setLogIn(true);
                return;
            }
        }
        throw new IllegalArgumentException("Password is Invalid!");
    }

    public void clickOnContent(User user, Content content) {
        if (!content.isPremiumContent() || (user.isLogIn() && (content.isPremiumContent() && user.isPremiumMember()))) {
            content.click(user);
        }
        if (!user.isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }
        if (content.isPremiumContent() && !user.isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
    }

    public Set<Content> getAllContent() {
        return new HashSet<>(allContent);
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(allUsers);
    }
}
