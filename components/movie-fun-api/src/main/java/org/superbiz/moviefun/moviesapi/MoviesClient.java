package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

public class MoviesClient {

    private String moviesUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }


    public MovieInfo find(Long id) {

        Map<String, Long> variables = new HashMap<>();
        variables.put("id", id);
        return restOperations.getForObject(moviesUrl, MovieInfo.class, variables);
    }


    public void addMovie(MovieInfo MovieInfo) {
        restOperations.postForEntity(moviesUrl, MovieInfo, MovieInfo.class);
    }


    public void deleteMovieId(long id) {

        restOperations.delete(moviesUrl + "/" + id);

    }


    public List<MovieInfo> getMovies() {

        return restOperations.exchange(moviesUrl, GET,null,movieListType).getBody();
    }

    public int countAll() {
        return restOperations.getForObject(moviesUrl + "/count", Integer.class);
    }


    public int count(String field, String key) {
        Map<String, String> variables = new HashMap<>();
        variables.put("field", field);
        variables.put("key", key);
        return restOperations.getForObject(moviesUrl, Integer.class, variables);
    }


    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations.exchange(builder.toUriString(), GET,null,movieListType).getBody();
    }


    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }


    public void clean() {

    }
}
