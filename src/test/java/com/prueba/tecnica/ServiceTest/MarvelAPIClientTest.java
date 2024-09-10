package com.prueba.tecnica.ServiceTest;

import com.prueba.tecnica.service.MarvelAPIClient;
import com.prueba.tecnica.service.Model.Comic;
import com.prueba.tecnica.service.Model.ComicData;
import com.prueba.tecnica.service.Model.ComicDataWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class MarvelAPIClientTest {

    @Mock
    private RestTemplate restTemplate;

    private MarvelAPIClient marvelAPIClient;

    private String baseUrl = "https://gateway.marvel.com/v1/public/";

    private String publicKey = "5a4d3eb67923d92b3d20610a88ba3067";

    private String privateKey = "78018bf8e16cd1ca48efeaaf9c02ca709dcf58bc";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        marvelAPIClient = new MarvelAPIClient(restTemplate, baseUrl, publicKey, privateKey);
    }

    @Test
    void obtenerListaComicsTest() {
        // Mock de la respuesta
        ComicDataWrapper comicDataWrapper = new ComicDataWrapper();
        ComicData comicData = new ComicData();
        List<Comic> mockComicList = new ArrayList<>();
        com.prueba.tecnica.service.Model.Comic mockComic = new com.prueba.tecnica.service.Model.Comic();
        mockComic.setId(1L);
        mockComic.setTitle("Spiderman");
        mockComic.setDescription("Spiderman comic");
        mockComicList.add(mockComic);
        comicData.setResults(mockComicList);
        comicDataWrapper.setComicData(comicData);

        String urlEsperada = baseUrl + "/comics?apikey=" + publicKey + "&ts=12345&hash=abcd1234";

        when(restTemplate.getForEntity(anyString(), eq(ComicDataWrapper.class)))
                .thenReturn(new ResponseEntity<>(comicDataWrapper, HttpStatus.OK));

        List<com.prueba.tecnica.models.Comic> comics = marvelAPIClient.obtenerListaComics();

        verify(restTemplate, times(1)).getForEntity(anyString(), eq(ComicDataWrapper.class));

        assertNotNull(comics);
        assertEquals(1, comics.size());
        assertEquals("Spiderman", comics.get(0).getTitle());
    }

    @Test
    void obtenerListaComicsVaciaTest() {
        ComicDataWrapper comicDataWrapper = new ComicDataWrapper();
        ComicData comicData = new ComicData();
        comicDataWrapper.setComicData(comicData);

        when(restTemplate.getForEntity(anyString(), eq(ComicDataWrapper.class)))
                .thenReturn(new ResponseEntity<>(comicDataWrapper, HttpStatus.OK));

        List<com.prueba.tecnica.models.Comic> comics = marvelAPIClient.obtenerListaComics();

        assertNotNull(comics);
        assertTrue(comics.isEmpty());
    }

}
