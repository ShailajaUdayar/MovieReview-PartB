package com.goanna.demo.moviereview.app.controller;

import com.goanna.demo.moviereview.app.model.MovieModel;
import com.goanna.demo.moviereview.app.repository.MovieRepository;
import com.goanna.demo.moviereview.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class WebAppController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private final MovieService movieService;

    public WebAppController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String viewHomePage(Model model) {
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        model.addAttribute("listMovies", allMovies);
        return "homepage";
    }

    private void populateFilterOptions(Model model, List<MovieModel> allMovies) {
        List<MovieModel> moviesList = allMovies;
        List<String> uniqueActors = moviesList.stream().map(MovieModel::getActor).distinct().collect(Collectors.toList());
        model.addAttribute("uniqueActors", uniqueActors);

        List<Double> minimumRating = Arrays.asList(10.0, 8.0, 6.0, 4.0, 2.0, 0.0);

        model.addAttribute("minimumRating", minimumRating);

        List<Integer> minimumYear = Arrays.asList(1980,1990,2000,2010,2020);

        model.addAttribute("minimumYear", minimumYear);
        List<String> uniqueTitle = moviesList.stream().map(MovieModel::getTitle).distinct().collect(Collectors.toList());
        model.addAttribute("uniqueTitle", uniqueTitle);

        List<String> uniqueGenre = moviesList.stream().map(MovieModel::getGenre).distinct().collect(Collectors.toList());
        model.addAttribute("uniqueGenre", uniqueGenre);
    }

    @GetMapping("/movies/genre/{genre}")
    public String filterByGenre(Model model, @PathVariable String genre) {
        List<MovieModel> moviesList = movieService.getMoviesByGenre(genre);
        model.addAttribute("listMovies", moviesList);
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        return "homepage";
    }

    @GetMapping("/movies/actor/{actor}")
    public String filterByActor(Model model, @PathVariable String actor) {
        List<MovieModel> moviesList = movieService.getMoviesByActor(actor);
        model.addAttribute("listMovies", moviesList);
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        return "homepage";
    }

    @GetMapping("/movies/title/{title}")
    public String filterByTitle(Model model, @PathVariable String title) {
        List<MovieModel> moviesList = movieService.getMoviesByTitle(title);
        model.addAttribute("listMovies", moviesList);
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        return "homepage";
    }

    @GetMapping("/movies/rating/{rating}")
    public String filterByMinimumRating(Model model, @PathVariable Double rating) {
        List<MovieModel> moviesList = movieService.getMoviesByMinimumRating(rating);
        model.addAttribute("listMovies", moviesList);
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        return "homepage";
    }

    @GetMapping("/movies/year/{year}")
    public String filterByMinimumYear(Model model, @PathVariable Integer year) {
        List<MovieModel> moviesList = movieService.getMoviesByMinimumYear(year);
        model.addAttribute("listMovies", moviesList);
        List<MovieModel> allMovies = movieService.getAllMovies();
        populateFilterOptions(model, allMovies);
        return "homepage";
    }

    @GetMapping("/movies/showNewMovieForm")
    public String showNewMovieForm(Model model) {
        MovieModel models = new MovieModel();
        model.addAttribute("model", models);
        return "new_movie";
    }

    @PostMapping("/movies/createMovieSubmitForm")
    public RedirectView createMovie(@ModelAttribute("model") MovieModel movieModel, Model model) {
        this.movieService.createMovie(movieModel);
        return redirectToHomePage();
    }

    @GetMapping("/movies/deleteMovie/{id}")
    public RedirectView deleteMovie(@PathVariable Integer id) {
        this.movieService.deleteMovie(id);
        return redirectToHomePage();
    }

    private RedirectView redirectToHomePage() {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/app");
        return redirectView;
    }

}