package com.prueba.tecnica.repository;


import com.prueba.tecnica.models.ComicFavorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComicFavoritoRepositorio extends JpaRepository<ComicFavorito, Long> {

    List<ComicFavorito> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndComicId(Long usuarioId, String comicId);
}
