package contentsite;

import java.util.Objects;

public class User {

    private String userName;
    private String password;
    private boolean isPremiumMember;
    private boolean isLogIn;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = Integer.toString((userName+password).hashCode());
    }

    public void upgradeForPremium() {
        isPremiumMember = true;
    }

    public void setLogIn(boolean logIn) {
        isLogIn = logIn;
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return Integer.parseInt(password);
    }

    public boolean isPremiumMember() {
        return isPremiumMember;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
