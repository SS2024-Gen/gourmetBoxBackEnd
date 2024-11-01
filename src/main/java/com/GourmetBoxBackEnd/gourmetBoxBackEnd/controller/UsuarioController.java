package com.GourmetBoxBackEnd.gourmetBoxBackEnd.controller;

import com.GourmetBoxBackEnd.gourmetBoxBackEnd.model.Producto;
import com.GourmetBoxBackEnd.gourmetBoxBackEnd.model.Usuario;
import com.GourmetBoxBackEnd.gourmetBoxBackEnd.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    //Obtener todos los usuarios

    //metodo para guardar un usuario
    @PostMapping("/usuario/crear")
    public String saveUsuario(@RequestBody Usuario usuario){
        usuarioService.saveUsuario(usuario);
        return "El usuario se creo con exito";
    }


    //verificar un usuario
    @GetMapping("/usuario/verificar/{userName}:{contrasena}")
    public int verificarUsuario(@PathVariable String userName,
                               @PathVariable String contrasena
                               ){
        List<Usuario> listaUsuarios =  usuarioService.getUsuarios();
        // 0 para decir que el usuario no se encontro
        int idUsuario = 0;

        for(Usuario usuarioDeLista: listaUsuarios){
            if(usuarioDeLista.getNombre_usuario().equals(userName)){
                if(usuarioDeLista.getContrasena().equals(contrasena)){
                    idUsuario = usuarioDeLista.getId_usuario();
                }else{
                    //menos uno para decir clave erronea
                    idUsuario = -1;
                }
                break;
            }
        }

        return idUsuario;

    }


    //obtener usuario
    @GetMapping("/usuario/obtener/{id}")
    public  Usuario findUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioService.findUsuarioById(id);
        return usuario;
    }


    //editar un usuario
    @PutMapping("/usuario/editar/{id}")
    public Usuario editUsuario(@PathVariable Integer id,
                                 @RequestParam(required = false, name="nombre") String nuevoNombre,
                                 @RequestParam(required = false, name="telefono") String nuevoTelefono,
                                 @RequestParam(required = false, name="correo") String nuevoCorreo
    ){
        usuarioService.editUsuario( id,
                nuevoNombre,
                nuevoTelefono,
                nuevoCorreo);
        Usuario usuario = usuarioService.findUsuarioById(id);
        return usuario;
    }


}