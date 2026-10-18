package com.netflix.contentservice.dto;

import com.netflix.contentservice.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;

    @NotNull(message = "genre is required")
    private Genre genre;

    private String director;
    private String cast;
    private int releaseYear;

    private String thumbnailUrl;
    private double rating;
    private int durationMinutes;
}
