package br.com.academy.model;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 5, max = 35, message = "O nome deve conter no minimo 5 caracteres")
    @NotBlank(message = "O nome não pode ser vazio.")
    @Column(name = "nome")
    private String nome;

    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo curso não pode ser nulo.")
    private Curso curso;

    @Column(name = "matricula")
    @NotNull(message = "Clique no Botão de Gerar.")
    @Size(min= 3, message="Clique no botão gerar matricula!")
    private String matricula;

    @Column(name = "status")
    @NotNull(message = "O campo status não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "turno")
    @NotBlank(message = "O turno não pode ser vazio.")
    @Size(min = 4, message = "No mínimo 4 caracteres.")
    private String turno;

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Enum getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
