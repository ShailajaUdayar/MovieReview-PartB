package com.goanna.demo.moviereview.app.service;

import com.goanna.demo.moviereview.app.domain.MovieDomain;
import com.goanna.demo.moviereview.app.model.MovieModel;
import com.goanna.demo.moviereview.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieModel> getAllMovies() {


        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> models = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            models.add(mapDomainToModel(domain));
        }

        return models;

    }

    private MovieModel mapDomainToModel(MovieDomain movieDomain) {
        MovieModel model = new MovieModel();
        model.setId(movieDomain.getId());
        model.setTitle(movieDomain.getTitle());
        model.setActor(movieDomain.getActor());
        model.setRating(movieDomain.getRating());
        model.setGenre(movieDomain.getGenre());
        model.setYear(movieDomain.getYear());
        return model;
    }

    private MovieDomain mapModelToDomain(MovieModel m) {
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
        MovieDomain domain = movieRepository.save(mapModelToDomain(movieModel));
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
                movies.add(mapDomainToModel(domain));

            }
        }
        return movies;
    }



    public List<MovieModel> getMoviesByGenre(String genre) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();

        for (MovieDomain domain : movieDomains) {
            if (domain.getGenre().equalsIgnoreCase(genre)) {
                movies.add(mapDomainToModel(domain));

            }
        }
        return movies;
    }


    public List<MovieModel> getMoviesByMinimumRating(int rating) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            if (domain.getRating() >= rating) {
                movies.add(mapDomainToModel(domain));

            }
        }
        return movies;
    }

    public List<MovieModel> getMoviesByMinimumYear(int year) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();
        for (MovieDomain domain : movieDomains) {
            if (domain.getYear() >= year) {
                movies.add(mapDomainToModel(domain));

            }
        }
        return movies;
    }

    public List<MovieModel> getMoviesByTitle(String title) {

        List<MovieDomain> movieDomains = movieRepository.findAll();

        List<MovieModel> movies = new ArrayList<>();

        for (MovieDomain domain : movieDomains) {
            if (domain.getTitle().equalsIgnoreCase(title)) {
                movies.add(mapDomainToModel(domain));

            }
        }
        return movies;
    }

    public Page< MovieModel > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.movieRepository.findAll(pageable).map(this::mapDomainToModel);
    }

    public MovieModel findMovie(Integer id) {
        return mapDomainToModel(
                movieRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("Movie not found")));
    }
}
