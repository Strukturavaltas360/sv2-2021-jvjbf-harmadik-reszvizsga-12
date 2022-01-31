package vehiclerental;

public class User {

    private String name;
    private String email;
    private int balance;


    public void decrease (int amount) {
        balance -= amount;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }
}
