package com.netflix.contentservice.repository;


import com.netflix.contentservice.dto.MovieResponse;
import com.netflix.contentservice.model.Genre;
import com.netflix.contentservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, String> {
    List<Movie> getMoviesByGenre(Genre genre);

    List<Movie> findByTitle(String title);
}
