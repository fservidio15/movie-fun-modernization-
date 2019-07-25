package org.superbiz.moviefun.movies;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMoviesBean {
    Movie find(Long id);

    @Transactional
    void addMovie(Movie movie);

    @Transactional
    void updateMovie(Movie movie);

    @Transactional
    void deleteMovie(Movie movie);

    @Transactional
    void deleteMovieId(long id);

    List<Movie> getMovies();

    List<Movie> findAll(int firstResult, int maxResults);

    int countAll();

    int count(String field, String searchTerm);

    List<Movie> findRange(String field, String searchTerm, int firstResult, int maxResults);

    void clean();
}
