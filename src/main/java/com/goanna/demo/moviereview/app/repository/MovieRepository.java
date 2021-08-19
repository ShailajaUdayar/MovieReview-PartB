package com.goanna.demo.moviereview.app.repository;

import com.goanna.demo.moviereview.app.domain.MovieDomain;
import com.goanna.demo.moviereview.app.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieDomain, Integer> {
    List<MovieModel> findByActorIgnoreCase(String actor);


}
