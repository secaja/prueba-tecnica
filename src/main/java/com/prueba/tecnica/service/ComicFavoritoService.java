package com.prueba.tecnica.service;

import com.prueba.tecnica.models.ComicFavorito;
import com.prueba.tecnica.models.Usuario;
import com.prueba.tecnica.repository.ComicFavoritoRepositorio;
import com.prueba.tecnica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicFavoritoService {
    @Autowired
    private ComicFavoritoRepositorio comicFavoritoRepositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ComicFavorito markAsFavorite(Long usuarioId, ComicFavorito comicFavorito) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        comicFavorito.setUsuario(usuario);

        if(!comicFavoritoRepositorio.existsByUsuarioIdAndComicId(usuarioId, comicFavorito.getComicId())){
            return comicFavoritoRepositorio.save(comicFavorito);
        }else{
            throw new RuntimeException("El comic ya esta marcado como favorito");
        }

    }

    public List<ComicFavorito> getFavoritesByUserId(Long usuarioId) {
        return comicFavoritoRepositorio.findByUsuarioId(usuarioId);
    }

}
