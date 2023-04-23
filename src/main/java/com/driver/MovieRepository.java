package com.driver;


import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    private final Map<String, Movie> movieMap = new HashMap<>();
    private final Map<String, Director> directorMap = new HashMap<>();
    private final Map<String, List<String>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        List<String> movies = directorMovieMap.getOrDefault(directorName, List.of());
        movies.add(movieName);
        directorMovieMap.put(directorName, movies);
    }

    public Movie getMovieByName(String name) {
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorMap.get(name);
    }


    public Optional<Movie> findMovieByName(String name) {
        return movieMap.values().stream()
                .filter(movie -> movie.getName().equals(name))
                .findFirst();
    }

    public Optional<Director> findDirectorByName(String name) {
        return directorMap.values().stream()
                .filter(director -> director.getName().equals(name))
                .findFirst();
    }


    public List<String> getMoviesByDirectorName(String directorName) {
        return directorMovieMap.getOrDefault(directorName, List.of());
    }

    public List<String> findAllMovies() {
        return movieMap.values().stream().map(Movie::getName).collect(Collectors.toList());
    }



    public void deleteDirectorByName(String name) {
        directorMovieMap.remove(name);
        directorMap.remove(name);
    }

    public void deleteAllDirectors() {
        directorMovieMap.clear();
        directorMap.clear();
    }


    public void delete(String movie) {
        movieMap.remove(movie);
    }

    public List<String> getAllDirectors() {
        return new ArrayList<>(directorMap.keySet());

    }
}
