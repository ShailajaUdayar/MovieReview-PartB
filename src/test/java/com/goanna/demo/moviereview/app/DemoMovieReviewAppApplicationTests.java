package com.goanna.demo.moviereview.app;

import com.goanna.demo.moviereview.app.controller.MovieController;
import com.goanna.demo.moviereview.app.model.MovieModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoMovieReviewAppApplicationTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void testGetAllMovies() {

        ResponseEntity<List<MovieModel>> allMoviesResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES,
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<MovieModel>>() {
        });

        assertNotNull(allMoviesResponse);
        assertEquals(HttpStatus.OK, allMoviesResponse.getStatusCode());
        List<MovieModel> movieModels = allMoviesResponse.getBody();
        assertNotNull(movieModels);

    }

    @Test
    public void testAddMovie() {

        MovieModel newMovie = new MovieModel();
        newMovie.setGenre("Horror");
        newMovie.setYear(2021);
        newMovie.setTitle("New Movie of 2021");
        newMovie.setActor("New movie actor");
        newMovie.setRating(8.8);
        HttpEntity<MovieModel> entity = new HttpEntity<>(newMovie);
        ResponseEntity<Integer> createdMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES,
                        HttpMethod.POST, entity,
                        Integer.class);

        assertNotNull(createdMovieResponse);
        assertEquals(HttpStatus.OK, createdMovieResponse.getStatusCode());
        Integer id = createdMovieResponse.getBody();
        assertNotNull(id);

        ResponseEntity<MovieModel> getMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES + "/" + id,
                        HttpMethod.GET, null,
                        MovieModel.class);

        assertNotNull(getMovieResponse);
        assertEquals(HttpStatus.OK, getMovieResponse.getStatusCode());
        MovieModel movie = getMovieResponse.getBody();
        assertNotNull(movie);
        assertEquals(id, movie.getId());
        assertEquals("Horror", movie.getGenre());
        assertEquals("New Movie of 2021", movie.getTitle());
        assertEquals(8.8, movie.getRating());
        assertEquals(2021, movie.getYear());
        assertEquals("New movie actor", movie.getActor());

        ResponseEntity<Void> deleteMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES + "/" + id,
                        HttpMethod.DELETE, null,
                        Void.class);

        assertNotNull(deleteMovieResponse);
        assertEquals(HttpStatus.NO_CONTENT, deleteMovieResponse.getStatusCode());
        assertNull(deleteMovieResponse.getBody());

    }


    @Test
    public void updateMovie() {

        MovieModel movie = new MovieModel();
        movie.setGenre("Horror");
        movie.setYear(2021);
        movie.setTitle("New Movie of 2021");
        movie.setActor("New movie actor");
        movie.setRating(8.8);
        HttpEntity<MovieModel> requestBody = new HttpEntity<>(movie);
        ResponseEntity<Integer> createdMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES,
                        HttpMethod.POST, requestBody,
                        Integer.class);

        assertNotNull(createdMovieResponse);
        assertEquals(HttpStatus.OK, createdMovieResponse.getStatusCode());
        Integer id = createdMovieResponse.getBody();
        assertNotNull(id);

        movie.setActor("Updated actor");

        ResponseEntity<Void> updateMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES + "/" + id,
                        HttpMethod.PUT, requestBody,
                        Void.class);

        ResponseEntity<MovieModel> getMovieResponse =
                testRestTemplate.exchange(
                        "http://localhost:" + port + MovieController.MOVIES + "/" + id,
                        HttpMethod.GET, null,
                        MovieModel.class);
        assertNotNull(getMovieResponse);
        assertEquals(HttpStatus.OK, getMovieResponse.getStatusCode());
        MovieModel updatedMovie = getMovieResponse.getBody();
        assertNotNull(updatedMovie);
        assertEquals("Updated actor", updatedMovie.getActor());
    }

}
