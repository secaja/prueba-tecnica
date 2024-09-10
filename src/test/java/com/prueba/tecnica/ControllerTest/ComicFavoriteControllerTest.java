package com.prueba.tecnica.ControllerTest;

import com.prueba.tecnica.controller.ComicFavoriteController;
import com.prueba.tecnica.models.ComicFavorito;
import com.prueba.tecnica.service.ComicFavoritoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class ComicFavoriteControllerTest {
    @Mock
    private ComicFavoritoService comicFavoritoService;

    @InjectMocks
    private ComicFavoriteController comicFavoriteController;

    public ComicFavoriteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarComicFavoritoTest() {
        Long usuarioId = 1L;
        ComicFavorito comicFavorito = new ComicFavorito();
        comicFavorito.setComicId("123L");

        when(comicFavoritoService.markAsFavorite(usuarioId, comicFavorito)).thenReturn(comicFavorito);

        ResponseEntity<ComicFavorito> response = comicFavoriteController.guardarComicFavorito(usuarioId, comicFavorito);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comicFavorito, response.getBody());
        verify(comicFavoritoService, times(1)).markAsFavorite(usuarioId, comicFavorito);
    }
    @Test
    void getFavoriteByUserIdTest() {
        Long usuarioId = 1L;
        ComicFavorito comicFavorito = new ComicFavorito();
        comicFavorito.setComicId("123L");
        List<ComicFavorito> favoritos = Collections.singletonList(comicFavorito);

        when(comicFavoritoService.getFavoritesByUserId(usuarioId)).thenReturn(favoritos);

        List<ComicFavorito> result = comicFavoriteController.getFavoriteByUserId(usuarioId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("123L", result.get(0).getComicId());
        verify(comicFavoritoService, times(1)).getFavoritesByUserId(usuarioId);
    }
}
