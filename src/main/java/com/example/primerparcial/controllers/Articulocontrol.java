package com.example.primerparcial.controllers;
import com.example.primerparcial.models.Articulo;
import com.example.primerparcial.models.Categoria;
import com.example.primerparcial.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class Articulocontrol {
    @Autowired
    private ArticuloRepository articuloRepository;
    @GetMapping(value = "/articulo/{id}")
    public ResponseEntity getArticulo(@PathVariable Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);

        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/articulo")
    public ResponseEntity crearArticulo(@RequestBody Articulo articulo){
        try {
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo,HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/articulo")
    public ResponseEntity listaArticulos(){
        List<Articulo> articulo = articuloRepository.findAll();
        if(articulo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulo,HttpStatus.OK);

    }
    @GetMapping("/articulo/codigo/{codigo}")
    public ResponseEntity listarPorNombre( @PathVariable String codigo){

        List<Articulo> usuarios = articuloRepository.findAllByCodigo(codigo);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @PutMapping("/articulo/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id ,@RequestBody Articulo usuario){
        Optional<Articulo> usuarioBD =articuloRepository.findById(id);
        if(usuarioBD.isPresent()){
            try {
                usuarioBD.get().setCodigo(usuario.getCodigo());
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setDescripcion(usuario.getDescripcion());
                usuarioBD.get().setCategoria(usuario.getCategoria());
                usuarioBD.get().setStock(usuario.getStock());
                usuarioBD.get().setPrecioVenta(usuario.getPrecioVenta());
                usuarioBD.get().setPrecioCompra(usuario.getPrecioCompra());

                articuloRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/articulo/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Articulo> usuarioBD =articuloRepository.findById(id);
        if(usuarioBD.isPresent()){
            articuloRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();

    }

}

