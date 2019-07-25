package org.superbiz.moviefun;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }


    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
    }


    public AlbumInfo find(long id)
    {
        Map<String, Long> variables = new HashMap<>();
        variables.put("id", id);
        return restOperations.getForObject(albumsUrl, AlbumInfo.class, variables);
    }

    public List<AlbumInfo> getAlbums() {

        return restOperations.exchange(albumsUrl, GET,null,albumListType).getBody();
    }

    public byte[] getAlbumCover(long albumId){
        return restOperations.getForObject(albumsUrl + "/" + albumId + "/cover", byte[].class);
    }

    @PostMapping("/{albumId}/cover")
    public String uploadCover(@PathVariable Long albumId, @RequestParam("file") MultipartFile uploadedFile) {
//        logger.debug("Uploading cover for album with id {}", albumId);
//
//        if (uploadedFile.getSize() > 0) {
//            try {
//                tryToUploadCover(albumId, uploadedFile);
//
//            } catch (IOException e) {
//                logger.warn("Error while uploading album cover", e);
//            }
//        }
//
//        return format("redirect:/albums/%d", albumId);
        return null;
    }


}
