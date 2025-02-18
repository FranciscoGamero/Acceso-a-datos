package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.UsuarioNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Usuario;
import com.salesianostriana.ProyectoConecta.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EntityManager entityManager;


    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario obternerUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }


    public List<Usuario> obtenerUsuarios(boolean borrado){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("borradoUsuarioFilter");
        filter.setParameter("isBorrado", borrado );
        List<Usuario> listaUsuario =  usuarioRepository.findAll();
        session.disableFilter("borradoUsuarioFilter");
        return listaUsuario;
    }


    public Usuario editarUsuario(Usuario usuario, Long id){
        return usuarioRepository.findById(id).map(old ->{
                old.setUsername(usuario.getUsername());
                    return usuarioRepository.save(old);
                }).orElseThrow(() -> new UsuarioNotFoundException("No hay usuario con la id: "+id));
    }

    public void borrarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
