package com.prueba.tecnica.repository;

import com.prueba.tecnica.dto.ProductoDTO;
import com.prueba.tecnica.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto save(Producto producto);

}
