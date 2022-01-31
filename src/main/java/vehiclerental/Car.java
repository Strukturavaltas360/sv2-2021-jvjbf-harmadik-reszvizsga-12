package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable{

    private String id;
    private int pricePerMinute;

    public Car(String id, int pricePerMinute) {
        this.id = id;
        this.pricePerMinute = pricePerMinute;
    }

    public Car(String id) {
        this.id = id;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * pricePerMinute;
    }

    @Override
    public LocalTime getRentingTime() {
        return null;
    }

    @Override
    public void rent(LocalTime time) {

    }

    @Override
    public void closeRent() {

    }
}
