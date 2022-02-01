package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    Set<User> users = new HashSet<>();
    Set<Rentable> rentables = new HashSet<>();
    Map<Rentable, User> actualRenting = new TreeMap<>();

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        } else {
            users.add(user);
        }
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {

        if (user.getBalance() < rentable.calculateSumPrice(180)) {
            throw new IllegalStateException("User don't have enough money");
        }
        if (rentable.getRentingTime() != null) {
            throw new IllegalStateException("Vehicle is already taken");
        }
        rentable.rent(time);
        actualRenting.put(rentable, user);
    }

    public void closeRent(Rentable rentable, int minutes) {
        User user = actualRenting.get(rentable);
        if (user != null) {
            actualRenting.remove(rentable);
            user.minusBalance(rentable.calculateSumPrice(minutes));
            rentable.closeRent();
        } else {
            throw new IllegalStateException("Cannot close rent");
        }
    }


    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public Set<Rentable> getRentables() {
        return new HashSet<>(rentables);
    }

    public Map<Rentable, User> getActualRenting() {
        return new TreeMap<>(actualRenting);
    }
}
