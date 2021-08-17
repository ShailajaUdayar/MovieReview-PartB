package com.goanna.demo.moviereview.app.controller;

import com.goanna.demo.moviereview.app.domain.MovieDomain;
import com.goanna.demo.moviereview.app.model.MovieModel;
import com.goanna.demo.moviereview.app.service.MovieService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }



    @GetMapping
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Integer createMovie(@RequestBody MovieModel movieModel) {
        return movieService.createMovie(movieModel);
    }


    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public Integer updateMovie(@RequestBody MovieModel movieModel, @PathVariable String id) {

       return movieService.updateMovie(movieModel);


    }

    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public void deleteMovie(@PathVariable Integer id) {
         movieService.deleteMovie(id);
    }


    @RequestMapping(method=RequestMethod.GET, value="/actor/{actor}")
    public List<MovieModel> getMoviesByActor(@PathVariable String actor){

        return movieService.getMoviesByActor(actor);
    }

    @RequestMapping(method=RequestMethod.GET, value="/genre/{genre}")
    public List<MovieModel> getMoviesByGenre(@PathVariable String genre){

        return movieService.getMoviesByGenre(genre);
    }

    @RequestMapping(method=RequestMethod.GET, value="/rating/{rating}")
    public List<MovieModel> getMoviesByMinimumRating(@PathVariable int rating) {

        return movieService.getMoviesByMinimumRating(rating);

    }

    @RequestMapping(method=RequestMethod.GET, value="/year/{year}")
    public List<MovieModel> getMoviesByMinimumYear(@PathVariable int year) {

        return movieService.getMoviesByMinimumYear(year);

    }

    @RequestMapping(method=RequestMethod.GET, value="/title/{title}")
    public List<MovieModel> getMoviesByTitle(@PathVariable String title) {

        return movieService.getMoviesByTitle(title);
    }
}
