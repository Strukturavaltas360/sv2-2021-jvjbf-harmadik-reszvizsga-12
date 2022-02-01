package vehiclerental;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Bike implements Rentable {

    private String id;
    private LocalTime rentingTime;

    public Bike(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * 15;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;

    }

    @Override
    public String toString() {
        return "Bike{" +
                "id='" + id + '\'' +
                ", rentingTime=" + rentingTime +
                '}';
    }
}
