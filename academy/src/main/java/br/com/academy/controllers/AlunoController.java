package br.com.academy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

import javax.validation.Valid;

@Controller
public class AlunoController {


    @Autowired
    private AlunoDao alunoRepository;

 
    @GetMapping("/inserirAlunos")
    public ModelAndView InsertAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("InsertAlunos")
    public ModelAndView insertAlunos(@Valid Aluno aluno, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("Aluno/formAluno");
            mv.addObject("aluno");
        }else{
        mv.setViewName("redirect:/alunos-adicionados");
        alunoRepository.save(aluno);
        }
        return mv;
    }

    @GetMapping("alunos-adicionados")
    public ModelAndView listagemAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/listAlunos");
        mv.addObject("listAlunos", alunoRepository.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alterar");
        Aluno aluno = alunoRepository.getOne(id);
        mv.addObject("aluno", aluno);
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        alunoRepository.save(aluno);
        mv.setViewName("redirect:/alunos-adicionados");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id){
        alunoRepository.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("/filtro-alunos")
    public ModelAndView filtroAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/filtroAlunos");
        return mv;
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listaAlunosAtivos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-ativos");
        mv.addObject("alunosAtivos", alunoRepository.findByStatusAtivos());
        return mv;
    }

    @GetMapping("alunos-inativos")
    public ModelAndView listaAlunoInativo(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-inativos");
        mv.addObject("alunosInativos", alunoRepository.findByStatusInativos());
        return mv;
    }

    @GetMapping("alunos-cancelados")
    public ModelAndView listaAlunoCancelados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-cancelados");
        mv.addObject("alunosCancelados", alunoRepository.findByStatusCancelados());
        return mv;
    }

    @GetMapping("alunos-trancados")
    public ModelAndView listaAlunoTrancados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-trancados");
        mv.addObject("alunosTrancados", alunoRepository.findByStatusTrancados());
        return mv;
    }

    @PostMapping("pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<Aluno> listaAlunos;
        if(nome == null || nome.trim().isEmpty()){
            listaAlunos = alunoRepository.findAll();
        }else{
            listaAlunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("listaDeAlunos", listaAlunos);
        mv.setViewName("Aluno/pesquisa-resultado");
        return mv;
    }



}
