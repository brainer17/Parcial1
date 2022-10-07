package com.example.primerparcial.repository;
import com.example.primerparcial.models.Articulo;
import com.example.primerparcial.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {

    List<Articulo> findAllByCodigo(String codigo);
    //List<Articulo> findAllByNombre(String nombre);


}