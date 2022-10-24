package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer>{
    
    @Query("select m from Aluno m where m.status = 'ATIVO' ")
    public List<Aluno> findByStatusAtivos();

    @Query("select m from Aluno m where m.status = 'INATIVO' ")
    public List<Aluno> findByStatusInativos();

    @Query("select m from Aluno m where m.status = 'CANCELADO' ")
    public List<Aluno> findByStatusCancelados();

    @Query("select m from Aluno m where m.status = 'TRANCADO' ")
    public List<Aluno> findByStatusTrancados();

    public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
