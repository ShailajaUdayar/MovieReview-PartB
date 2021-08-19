package com.goanna.demo.moviereview.app.controller;

import com.goanna.demo.moviereview.app.model.MovieModel;
import com.goanna.demo.moviereview.app.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(MovieController.MOVIES)
public class MovieController {

    public static final String MOVIES = "/movies";
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public ResponseEntity<List<MovieModel>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<Integer> createMovie(@RequestBody MovieModel movieModel) {
        return ResponseEntity.ok(movieService.createMovie(movieModel));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<MovieModel> getMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.findMovie(id));


    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> updateMovie(@RequestBody MovieModel movieModel, @PathVariable Integer id) {
        movieModel.setId(id);
        movieService.updateMovie(movieModel);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/actor/{actor}")
    public ResponseEntity<List<MovieModel>> getMoviesByActor(@PathVariable String actor) {

        return ResponseEntity.ok(movieService.getMoviesByActor(actor));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/genre/{genre}")
    public ResponseEntity<List<MovieModel>> getMoviesByGenre(@PathVariable String genre) {

        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rating/{rating}")
    public ResponseEntity<List<MovieModel>> getMoviesByMinimumRating(@PathVariable Integer rating) {

        return ResponseEntity.ok(movieService.getMoviesByMinimumRating(rating));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/year/{year}")
    public ResponseEntity<List<MovieModel>> getMoviesByMinimumYear(@PathVariable Integer year) {

        return ResponseEntity.ok(movieService.getMoviesByMinimumYear(year));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    public ResponseEntity<List<MovieModel>> getMoviesByTitle(@PathVariable String title) {

        return ResponseEntity.ok(movieService.getMoviesByTitle(title));
    }
}
