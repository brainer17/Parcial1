package com.procesos.negocio.shirly.controllers;

import com.procesos.negocio.shirly.models.Usuario;
import com.procesos.negocio.shirly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Date;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){

        Optional<Usuario> usuario= usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();




        // Usuario usuario = new Usuario();
        //usuario.setId(id);
        //usuario.setNombre("Shirly");
        //usuario.setApellidos("Trigos");
        //usuario.setDocumento("1193557572");
        //usuario.setDireccion("Juan 23");
        //usuario.setFechaNacimiento(new Date(2002,9,21));
        //usuario.setTelefono("3042047497");

    }
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
       try {
           usuarioRepository.save(usuario);
           return new ResponseEntity(usuario,HttpStatus.CREATED);
       }catch(Exception e){
           return ResponseEntity.badRequest().build();
       }
    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(){
        List<Usuario> usuarios= usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){

        List<Usuario> usuarios=usuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);

    } @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos) {
        List<Usuario> usuarios=usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);


    }
        @GetMapping("/usuario/nombre/{nombre}")
        public ResponseEntity listarPorNombres(@PathVariable String nombre) {
            List<Usuario> usuarios=usuarioRepository.findAllByApellidos(nombre);{
                if(usuarios.isEmpty()){
                    return ResponseEntity.notFound().build();
                }
                return new ResponseEntity(usuarios,HttpStatus.OK);
            }



}
    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,
                                          @RequestBody Usuario usuario){
        Optional<Usuario> usuarioBD= usuarioRepository.findById(id);
        if (usuarioBD.isPresent()){
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD, HttpStatus.OK);
            }catch ( Exception e){
                return  ResponseEntity.badRequest().build();
            }
        }
            return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD= usuarioRepository.findById(id);
        if (usuarioBD.isPresent()){
            usuarioRepository.delete(usuarioBD.get());
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
