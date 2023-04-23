package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    // Add a movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    // Add a director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new  ResponseEntity<>("Director added successfully",HttpStatus.CREATED);
    }

    // Pair an existing movie and director
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>("Movie-Director pair added successfully",HttpStatus.CREATED);
    }

    // Get Movie by movie name
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

    // Get Director by director name
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.OK);
    }

    // Get List of movies name for a given director name
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        List<String> movies = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    // Get List of all movies added
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    // Delete a director and its movies from the records
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName) {
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Director and associated movies deleted successfully",HttpStatus.OK);
    }

    // Delete all directors and all movies by them from the records
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors and associated movies deleted successfully",HttpStatus.OK);
    }
}
