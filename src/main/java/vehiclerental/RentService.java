package vehiclerental;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RentService {

    Set<User> users = new HashSet<>();
    Set<Rentable> rentables = new HashSet<>();
    Map<Rentable, User> orders = new HashMap<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

}
