package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    @Query("select m from Usuario m where m.email = :email")
    public Usuario findByEmail(String email);

    @Query("select m from Usuario m where m.user = :user and m.senha = :senha")
    public Usuario buscarLogin(String user, String senha);

}
