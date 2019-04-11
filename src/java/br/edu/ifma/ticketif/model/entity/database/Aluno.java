package br.edu.ifma.ticketif.model.entity.database;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
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

    @Column(name = "AL_FONE")
    private Integer fone;

    @Column(name = "AL_EMAIL")
    private String email;

    @Column(name = "AL_SEXO")
    private String sexo;

    @Column(name = "AL_DATA_NASC")
    private String dataNasc;

    @Column(name = "AL_TIPO")
    private String tipo;

    @Column(name = "AL_CURSO")
    private String curso;

    @Column(name = "AL_TURMA")
    private String turma;

    @Column(name = "AL_ANO_ENTRADA")
    private Integer anoEntrada;

    @Column(name = "AL_ANO_SAIDA")
    private Integer anoSaida;

    public Integer getFone() {
        return fone;
    }

    public void setFone(Integer fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Integer getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(Integer anoEntrada) {
        this.anoEntrada = anoEntrada;
    }

    public Integer getAnoSaida() {
        return anoSaida;
    }

    public void setAnoSaida(Integer anoSaida) {
        this.anoSaida = anoSaida;
    }

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