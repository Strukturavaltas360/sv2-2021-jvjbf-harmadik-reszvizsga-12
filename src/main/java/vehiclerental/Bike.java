package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable{

    private int rentingTime;

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) rentingTime*15;
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
