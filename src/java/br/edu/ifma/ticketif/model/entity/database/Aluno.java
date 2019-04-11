package br.edu.ifma.ticketif.model.entity.database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "DB_ALUNO")
public class Aluno implements Serializable {

    @Column(name = "AL_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AL_NOME")
    private String nome;

    @Column(name = "AL_CPF")
    private String cpf;

    @Column(name = "AL_MATRICULA")
    private String matricula;

    @Column(name = "AL_DIGITAL")
    private String digital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital1) {
        this.digital = digital1;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}