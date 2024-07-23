package com.prueba.tecnica.service;

import com.prueba.tecnica.models.Comic;
import com.prueba.tecnica.service.Model.ComicDataWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MarvelAPIClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String publicKey;
    private final String privateKey;

    public MarvelAPIClient(RestTemplate restTemplate,
                           @Value("${marvel.api.base-url}") String baseUrl,
                           @Value("${marvel.api.public-key}") String publicKey,
                           @Value("${marvel.api.private-key}") String privateKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public List<Comic> obtenerListaComics() {
        String resourceUrl = baseUrl + "/comics";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        // Construir el hash MD5 para la autenticación
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String hash = MD5(timestamp + privateKey + publicKey);

        // Construir la URL completa con los parámetros requeridos
        String url = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("apikey", publicKey)
                .queryParam("ts", timestamp)
                .queryParam("hash", hash)
                .toUriString();
        ResponseEntity<ComicDataWrapper> responseEntity = restTemplate.getForEntity(url, ComicDataWrapper.class);
        return transformarData(responseEntity.getBody());
    }

    private List<Comic> transformarData(ComicDataWrapper comicDataWrapper) {

        List<Comic> comics = new ArrayList<>();
        Comic comic;
        for (com.prueba.tecnica.service.Model.Comic item: comicDataWrapper.getComicData().getResults()){
            comic = new Comic();
            comic.setId(item.getId());
            comic.setDescription(item.getDescription());
            comic.setTitle(item.getTitle());
            if (item.getThumbnail() != null && item.getThumbnail().getPath() != null && !item.getThumbnail().getPath().isEmpty()
                    && item.getThumbnail().getExtension() != null && !item.getThumbnail().getExtension().isEmpty()) {
                String pathWithExtension = item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension();
                comic.setUrlImagen(pathWithExtension);
            }
            comic.setModified(item.getModified());
            comics.add(comic);
        }
        return comics;
    }
    private String MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
