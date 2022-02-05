package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApartmentRental {

    List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String location) {
        return apartments.stream()
                .filter(apartment -> apartment.getLocation().equals(location))
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String... extra) {
        return apartments.stream()
                .filter(apartment -> apartment.getExtras().containsAll(Arrays.asList(extra)))
                .toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartments.stream()
                .anyMatch(apartment -> apartment.getBathRoomType().equals(bathRoomType));
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream()
                .map(apartment -> apartment.getSize())
                .toList();
    }

}
