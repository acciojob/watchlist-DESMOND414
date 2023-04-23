package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    // Add a movie
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    // Add a director
    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    // Pair an existing movie and director
    public void addMovieDirectorPair(String movieName, String directorName) {
        Optional<Movie> optionalMovie = movieRepository.findMovieByName(movieName);
        Optional<Director> optionalDirector = movieRepository.findDirectorByName(directorName);

        if (optionalMovie.isPresent() && optionalDirector.isPresent()) {
            Movie movie = optionalMovie.get();
            Director director = optionalDirector.get();
            movieRepository.addDirector(director);
            movieRepository.addMovie(movie);
        } else {
            throw new IllegalArgumentException("Either movie or director not found");
        }
    }

    // Get Movie by movie name
    public Movie getMovieByName(String name) {
        Optional<Movie> optionalMovie = movieRepository.findMovieByName(name);

        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }

    // Get Director by director name
    public Director getDirectorByName(String name) {
        Optional<Director> optionalDirector = movieRepository.findDirectorByName(name);

        if (optionalDirector.isPresent()) {
            return optionalDirector.get();
        } else {
            throw new IllegalArgumentException("Director not found");
        }
    }

    // Get List of movies name for a given director name
    public List<String> getMoviesByDirectorName(String directorName) {
        Optional<Director> optionalDirector = movieRepository.findDirectorByName(directorName);

        if (optionalDirector.isPresent()) {
            return movieRepository.getMoviesByDirectorName(directorName);
        } else {
            throw new IllegalArgumentException("Director not found");
        }
    }

    // Get List of all movies added
    public List<String> findAllMovies() {
        List<String> movies = movieRepository.findAllMovies();
        return movies;
    }

    // Delete a director and its movies from the records
    public void deleteDirectorByName(String directorName) {
        Optional<Director> optionalDirector = movieRepository.findDirectorByName(directorName);

        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();
            List<String> movies = movieRepository.getMoviesByDirectorName(directorName);

            // Delete all movies associated with this director
            for (String movie : movies) {
                movieRepository.delete(movie);
            }

            // Delete the director itself
            movieRepository.deleteDirectorByName(directorName);
        } else {
            throw new IllegalArgumentException("Director not found");
        }
    }

    // Delete all directors and all movies by them from the records
    public void deleteAllDirectors() {
//        List<Director> directors = movieRepository.findAllDirectors();
//
//        // Delete all movies associated with each director
//        for (Director director : directors) {
//            List<Movie> movies = director.getMovies();
//            for (Movie movie : movies) {
//                movieRepository.delete(movie);
//            }
//        }
        List<String> directors=movieRepository.getAllDirectors();
        for(String directorsName: directors){
            deleteDirectorByName(directorsName);
        }
        // Delete all directors
//        movieRepository.getAllDirectors();
//        movieRepository.deleteAllDirectors();
    }

}
