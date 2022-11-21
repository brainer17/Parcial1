package com.procesos.negocio.shirly.repository;

import com.procesos.negocio.shirly.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByNombre( String nombre);
    List<Usuario> findAllByApellidos( String apellidos);
    List<Usuario> findAllByNombreAndApellidos(String nombre, String apellidos);
}

