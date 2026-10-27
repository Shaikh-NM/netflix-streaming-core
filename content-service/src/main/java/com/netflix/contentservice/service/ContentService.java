package com.netflix.contentservice.service;

import com.netflix.contentservice.dto.MovieRequest;
import com.netflix.contentservice.dto.MovieResponse;
import com.netflix.contentservice.model.Genre;
import com.netflix.contentservice.model.Movie;
import com.netflix.contentservice.model.VideoStatus;
import com.netflix.contentservice.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentService {
    private final MoviesRepository moviesRepository;

    public MovieResponse addMovie(MovieRequest request) {
        log.info("adding new movie : {}", request.getTitle());
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setDirector(request.getDirector());
        movie.setCast(request.getCast());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setRating(request.getRating());
        movie.setThumbnailUrl(request.getThumbnailUrl());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setVideoStatus(VideoStatus.PENDING);

        Movie savedMovie = moviesRepository.save(movie);
        log.info("Movie added with ID : {}", savedMovie.getId());

        return mapToResponse(savedMovie);
    }

    public List<MovieResponse> getAllMovies() {
        return moviesRepository.findAll().stream()
                .map(movie -> mapToResponse(movie))
                .toList();
    }

    public MovieResponse getMovieById(String movieId) {
        Movie movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found : " + movieId));

        return mapToResponse(movie);
    }

    public List<MovieResponse> getMovieByGenre(Genre genre) {
        return moviesRepository.getMoviesByGenre(genre).stream()
                .map(movie -> mapToResponse(movie))
                .toList();
    }

    public List<MovieResponse> searchMovies(String title) {
        List<Movie> movies = moviesRepository.findByTitle(title);
        return movies.stream()
                .map(movie -> mapToResponse(movie))
                .toList();
    }

    public void updateVideoKey(String movieId, String videoKey) {
        log.info("updating video key for movie : {}", movieId);
        Movie movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found : " + movieId));

        movie.setVideoKey(videoKey);
        movie.setVideoStatus(VideoStatus.UPLOADED);
        moviesRepository.save(movie);
    }

    public void updateHlsUrl(String movieId, String hlsUrl) {
        log.info("updating HLS url for movie : {}", movieId);
        Movie movie = moviesRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found : " + movieId));

        movie.setHlsUrl(hlsUrl);
        movie.setVideoStatus(VideoStatus.READY);
        moviesRepository.save(movie);

        log.info("Movie {} is now ready for streaming", movieId);
    }

    private MovieResponse mapToResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setDescription(movie.getDescription());
        response.setGenre(movie.getGenre());
        response.setDirector(movie.getDirector());
        response.setCast(movie.getCast());
        response.setReleaseYear(movie.getReleaseYear());
        response.setRating(movie.getRating());
        response.setThumbnailUrl(movie.getThumbnailUrl());
        response.setDurationMinutes(movie.getDurationMinutes());
        response.setVideoKey(movie.getVideoKey());
        response.setVideoStatus(movie.getVideoStatus());
        response.setHlsUrl(movie.getHlsUrl());
        response.setCreatedAt(movie.getCreatedAt());

        return response;
    }


}
