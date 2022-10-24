package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.Exception.CriptoExistException;
import br.com.academy.Exception.EmailExistsException;
import br.com.academy.Exception.ServiceExc;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioRepository;

    public void salvarUsuario(Usuario user) throws Exception {

        try {

            if(usuarioRepository.findByEmail(user.getEmail()) != null){
                throw new EmailExistsException("Já existe um email cadastrado para: " + user.getEmail());
            }

            user.setSenha(Util.md5(user.getSenha()));
            
        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistException("Erro na criptografia da senha");
        }
    
    usuarioRepository.save(user);
}

public Usuario loginUser(String user, String senha) throws ServiceExc {
    Usuario userLogin =usuarioRepository.buscarLogin(user, senha);
    return userLogin;
}

}
    

