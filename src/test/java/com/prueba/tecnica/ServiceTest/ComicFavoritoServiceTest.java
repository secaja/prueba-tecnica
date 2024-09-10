package com.prueba.tecnica.ServiceTest;

import com.prueba.tecnica.models.ComicFavorito;
import com.prueba.tecnica.models.Usuario;
import com.prueba.tecnica.repository.ComicFavoritoRepositorio;
import com.prueba.tecnica.repository.UsuarioRepository;
import com.prueba.tecnica.service.ComicFavoritoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringJUnitConfig
@SpringBootTest
public class ComicFavoritoServiceTest {
    @Mock
    private ComicFavoritoRepositorio comicFavoritoRepositorio;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ComicFavoritoService comicFavoritoService;

    public ComicFavoritoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void markAsFavoriteTest() {
        Long usuarioId = 1L;
        String comicId = "123L";
        Usuario usuario = new Usuario();
        ComicFavorito comicFavorito = new ComicFavorito();
        comicFavorito.setComicId(comicId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(comicFavoritoRepositorio.existsByUsuarioIdAndComicId(usuarioId, comicId)).thenReturn(false);
        when(comicFavoritoRepositorio.save(comicFavorito)).thenReturn(comicFavorito);

        ComicFavorito result = comicFavoritoService.markAsFavorite(usuarioId, comicFavorito);

        assertNotNull(result);
        assertEquals(comicId, result.getComicId());
        verify(comicFavoritoRepositorio, times(1)).save(comicFavorito);
    }

    @Test
    void markAsFavoriteTestYaEstaRegistradoComFavorito() {
        Long usuarioId = 1L;
        String comicId = "123L";
        Usuario usuario = new Usuario();
        ComicFavorito comicFavorito = new ComicFavorito();
        comicFavorito.setComicId(comicId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(comicFavoritoRepositorio.existsByUsuarioIdAndComicId(usuarioId, comicId)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            comicFavoritoService.markAsFavorite(usuarioId, comicFavorito);
        });

        assertEquals("El comic ya esta marcado como favorito", exception.getMessage());
        verify(comicFavoritoRepositorio, never()).save(any(ComicFavorito.class));
    }
    @Test
    void getFavoritesByUserIdTest() {
        Long usuarioId = 1L;
        ComicFavorito comicFavorito = new ComicFavorito();
        comicFavorito.setComicId("123L");

        when(comicFavoritoRepositorio.findByUsuarioId(usuarioId)).thenReturn(Collections.singletonList(comicFavorito));

        List<ComicFavorito> result = comicFavoritoService.getFavoritesByUserId(usuarioId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("123L", result.get(0).getComicId());
    }
}
