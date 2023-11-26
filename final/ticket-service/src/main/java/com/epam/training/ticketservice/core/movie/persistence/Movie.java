package com.epam.training.ticketservice.core.movie.persistence;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Movie {

    @Column(unique = true)
    private String name;
    private String genre;
    private int duration;

    public Movie(MovieDto movieDto) {
        this.name = movieDto.getName();
        this.genre = movieDto.getGenre();
        this.duration = movieDto.getDuration();
    }

    public Movie(String name, String genre, Integer duration) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }
}