package com.netflix.contentservice.controller;

import com.netflix.contentservice.dto.MovieRequest;
import com.netflix.contentservice.dto.MovieResponse;
import com.netflix.contentservice.model.Genre;
import com.netflix.contentservice.model.Movie;
import com.netflix.contentservice.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
@Slf4j
public class ContentController {
    private final ContentService contentService;

    @PostMapping("/movie")
    public ResponseEntity<MovieResponse> addMovie(MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contentService.addMovie(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(contentService.getAllMovies());
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable String movieId) {
        return ResponseEntity.ok(contentService.getMovieById(movieId));
    }

    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<List<MovieResponse>> getMovieByGenre(@PathVariable Genre genre) {
        return ResponseEntity.ok(contentService.getMovieByGenre(genre));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(contentService.searchMovies(title));
    }
}
