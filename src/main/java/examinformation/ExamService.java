package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private static final String SEPARATOR = ";";

    private Map<String, ExamResult> results = new TreeMap<>();

    private int theoryMax;
    private int practiceMax;


    public void readFromFIle(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String line = bf.readLine();
            theoryMax = Integer.parseInt(line.split(" ")[0]);
            practiceMax = Integer.parseInt(line.split(" ")[1]);
            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(SEPARATOR);
                String name = fields[0];
                String[] temp = fields[1].split(" ");
                results.put(name, new ExamResult(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + ioe.getMessage(), ioe);
        }
    }

    public List<String> findPeopleFailed() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            if (entry.getValue().getTheory() < 0.51 * getTheoryMax() || entry.getValue().getPractice() < 0.51 * getPracticeMax()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public String findBestPerson() {
        String bestPerson = "";
        int bestResult = 0;

        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            if (entry.getValue().getPractice() + entry.getValue().getTheory() > bestResult &&
                    (entry.getValue().getTheory() > 0.51 * getTheoryMax() && entry.getValue().getPractice() > 0.51 * getPracticeMax())) {
                bestPerson = entry.getKey();
                bestResult = entry.getValue().getPractice() + entry.getValue().getTheory();
            }
        }

        return bestPerson;
    }

    public Map<String, ExamResult> getResults() {
        return new TreeMap<>(results);
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }
}
