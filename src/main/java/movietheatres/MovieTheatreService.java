package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {
    private static final String SEPARATOR = "-";
    private static final String SEPARATOR2 = ";";

    Map<String, List<Movie>> shows = new TreeMap<>();

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                createEntryFromLine(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error while reading file!", ioe);
        }
    }

    public List<String> findMovie(String title) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<Movie>> entry : shows.entrySet()) {
            for (Movie m : entry.getValue())
                if (m.getTitle().equals(title) && !result.contains(entry.getKey())) {
                    result.add(entry.getKey());
                }
        }
        Collections.sort(result);
        return result;
    }

    public LocalTime findLatestShow(String title) {
        return shows.values().stream()
                .flatMap(movies -> movies.stream())
                .filter(movie -> movie.getTitle().equals(title))
                .max(Comparator.comparing(Movie::getStartTime))
                .map(movie -> movie.getStartTime())
                .orElseThrow(() -> new IllegalArgumentException("Empty list"));
    }


    private void createEntryFromLine(String line) {
        String[] lineFields = line.split(SEPARATOR);
        String theatre = lineFields[0];
        String[] movieFields = lineFields[1].split(SEPARATOR2);
        String movieTitle = movieFields[0];
        LocalTime startTime = LocalTime.parse(movieFields[1]);
        findAndInsert(theatre, movieTitle, startTime);
    }

    private void findAndInsert(String theatre, String movieTitle, LocalTime startTime) {
        List<Movie> actual = shows.get(theatre);

        if (actual == null) {
            actual = new ArrayList<>();
            shows.put(theatre, actual);
        }
        actual.add(new Movie(movieTitle, startTime));
    }

    public Map<String, List<Movie>> getShows() {
        return new HashMap<>(shows);
    }
}
