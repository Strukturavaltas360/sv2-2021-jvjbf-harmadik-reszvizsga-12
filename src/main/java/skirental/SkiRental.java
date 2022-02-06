package skirental;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class SkiRental {

    private static final String SEPARATOR = ";";
    private Map<String, Equipment> rentals = new TreeMap<>();

    public void loadFromFile(Path path) {
        try (Scanner scanner = new Scanner(path)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(SEPARATOR);
                String[] equipsString = fields[1].split(" ");
                int sizeOfSki = Integer.parseInt(equipsString[0]);
                int sizeOfBoot = Integer.parseInt(equipsString[1]);
                rentals.put(fields[0], new Equipment(sizeOfSki, sizeOfBoot));
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path.toString());
        }
    }

    public List<String> listChildren() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Equipment> actual : rentals.entrySet()) {
            if ((actual.getValue().getSizeOfBoot() > 0 && actual.getValue().getSizeOfBoot() <= 37) && (actual.getValue().getSizeOfSkis() > 0 && actual.getValue().getSizeOfSkis() <= 120)) {
                result.add(actual.getKey());
            }
        }
        return result;
    }

    public String getNameOfPeopleWithBiggestFoot() {
        String resultName = "";
        int biggestFoot = 0;
        for (Map.Entry<String, Equipment> actual : rentals.entrySet()) {
            if (actual.getValue().getSizeOfBoot() > biggestFoot && actual.getValue().getSizeOfSkis() > 0) {
                biggestFoot = actual.getValue().getSizeOfBoot();
                resultName = actual.getKey();
            }
        }
        return resultName;
    }


    public Map<String, Equipment> getRentals() {
        return new TreeMap<>(rentals);
    }
}