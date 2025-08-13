package MovieTicketBookingSystem.repository;
import MovieTicketBookingSystem.model.Movie;
import MovieTicketBookingSystem.util.IdGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private static MovieRepository instance = null;
    private Map<Integer, Movie> movieMap;

    private MovieRepository() {
        this.movieMap = new HashMap<>();

        Movie movie1 = new Movie(
                IdGenerator.getId(),                  // unique movie ID
                "Inception",                          // name
                "A skilled thief who enters the dreams of others to steal secrets.", // description
                LocalDate.of(2010, 7, 16),             // release date
                148                                    // duration in minutes
        );
        movieMap.put(movie1.id, movie1);
    }

    public static MovieRepository getInstance() {
        if(instance == null) {
            synchronized(MovieRepository.class) {
                if(instance == null)
                    instance = new MovieRepository();
            }
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        this.movieMap.put(movie.id, movie);
    }

    public Movie getMovie(int id) {
        return this.movieMap.getOrDefault(id, null);
    }
}
