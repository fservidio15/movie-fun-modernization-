package org.superbiz.moviefun.moviesapi;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMoviesBean {
    MovieInfo find(Long id);

    @Transactional
    void addMovie(MovieInfo MovieInfo);

    @Transactional
    void updateMovie(MovieInfo MovieInfo);

    @Transactional
    void deleteMovie(MovieInfo MovieInfo);

    @Transactional
    void deleteMovieId(long id);

    List<MovieInfo> getMovies();

    List<MovieInfo> findAll(int firstResult, int maxResults);

    int countAll();

    int count(String field, String searchTerm);

    List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults);

    void clean();
}
