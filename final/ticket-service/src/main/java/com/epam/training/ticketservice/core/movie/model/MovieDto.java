package com.epam.training.ticketservice.core.movie.model;

import lombok.Value;

@Value
public class MovieDto {

    String name;
    String genre;
    Integer duration;

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public static class MovieBuilder {

        String name;
        String genre;
        Integer duration;

        public MovieBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public MovieBuilder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public MovieBuilder withDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public MovieDto build() {
            return new MovieDto(name, genre, duration);
        }
    }
}
