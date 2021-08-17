package com.goanna.demo.moviereview.app.service;

import com.goanna.demo.moviereview.app.domain.MovieDomain;
import com.goanna.demo.moviereview.app.model.MovieModel;
import com.goanna.demo.moviereview.app.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieModel> getAllMovies() {


        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> models = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            models.add(map(domain));
        }

        return models;

    }

    private MovieModel map(MovieDomain movieDomain) {
        MovieModel model = new MovieModel();
        model.setId(movieDomain.getId());
        model.setTitle(movieDomain.getTitle());
        model.setActor(movieDomain.getActor());
        model.setRating(movieDomain.getRating());
        model.setGenre(movieDomain.getGenre());
        model.setYear(movieDomain.getYear());
        return model;
    }

    private MovieDomain map(MovieModel m) {
        MovieDomain d = new MovieDomain();
        d.setTitle(m.getTitle());
        d.setActor(m.getActor());
        d.setRating(m.getRating());
        d.setGenre(m.getGenre());
        d.setYear(m.getYear());
        return d;
    }

    private MovieDomain update(MovieModel m) {
        MovieDomain d = new MovieDomain();
        d.setId(m.getId());
        d.setTitle(m.getTitle());
        d.setActor(m.getActor());
        d.setRating(m.getRating());
        d.setGenre(m.getGenre());
        d.setYear(m.getYear());
        return d;
    }



    public Integer createMovie(MovieModel movieModel) {
        MovieDomain domain = movieRepository.save(map(movieModel));
        return domain.getId();
    }

    public Integer updateMovie(MovieModel movieModel) {
        MovieDomain domain = movieRepository.save(update(movieModel));
        return domain.getId();

    }

    public void deleteMovie(Integer id) {
        //movieRepository.delete(movieRepository.getById(id));
        this.movieRepository.deleteById(id);

    }

    public List<MovieModel> getMoviesByActor(String actor) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();

        for (MovieDomain domain : movieDomains) {
            if (domain.getActor().equalsIgnoreCase(actor)) {
                movies.add(map(domain));
            }
        }
        return movies;
    }


    public List<MovieModel> getMoviesByGenre(String genre) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();

        for (MovieDomain domain : movieDomains) {
            if (domain.getGenre().equalsIgnoreCase(genre)) {
                movies.add(map(domain));

            }
        }
        return movies;
    }


    public List<MovieModel> getMoviesByMinimumRating(int rating) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            if (domain.getRating() >= rating) {
                movies.add(map(domain));

            }
        }
        return movies;
    }

    public List<MovieModel> getMoviesByMinimumYear(int year) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            if (domain.getYear() >= year) {
                movies.add(map(domain));

            }
        }
        return movies;
    }

    public List<MovieModel> getMoviesByTitle(String title) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();

        for (MovieDomain domain : movieDomains) {
            if (domain.getTitle().equalsIgnoreCase(title)) {
                movies.add(map(domain));

            }
        }
        return movies;
    }

}
