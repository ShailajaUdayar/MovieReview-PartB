package com.goanna.demo.moviereview.app.repository;

import com.goanna.demo.moviereview.app.domain.MovieDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieDomain, Integer> {
}
