package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.Exception.ServiceExc;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.UsuarioService;
import br.com.academy.util.Util;

@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDao usuarioRepository;

    @GetMapping("/login")
    public ModelAndView loginUser(Usuario usuario){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("usuario", usuario);
        return mv;
    } 

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

  @GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno",new Aluno());
		return mv;
	}

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("Login/cadastro");
        return mv;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception{
        ModelAndView mv = new ModelAndView();
        usuarioService.salvarUsuario(usuario);
        mv.setViewName("redirect:/login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc{
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        if(br.hasErrors()){
            mv.setViewName("Login/login");
        }

        Usuario userLogin = usuarioService.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
        if(userLogin == null){
            mv.addObject("msg", "Usu??rio n??o encontrodo. Tente novamente");
        }else{
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        return mv;
}

@PostMapping("/logout")
public ModelAndView logout(HttpSession session){
    session.invalidate();
    return login();
}
}

