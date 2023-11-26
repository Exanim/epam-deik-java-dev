package com.epam.training.ticketservice.core.movie;


import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.movie.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll()
                .stream()
                .map(this::createEntityFromDto)
                .toList();
    }

    @Override
    public Optional<MovieDto> getMovieByName(String movieName) {
        return createEntityFromDto(movieRepository.findByName(movieName));
    }

    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie(movieDto);
        movieRepository.save(movie);
    }

    private MovieDto createEntityFromDto(Movie movie) {
        return MovieDto.builder()
                .withName(movie.getName())
                .withGenre(movie.getGenre())
                .withDuration(movie.getDuration())
                .build();
    }

    private Optional<MovieDto> createEntityFromDto(Optional<Movie> movie) {
        return movie.map(this::createEntityFromDto);
    }
}
