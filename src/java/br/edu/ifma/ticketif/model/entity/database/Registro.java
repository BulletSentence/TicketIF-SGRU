package br.edu.ifma.ticketif.model.entity.database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity

@Table (name = "DB_REGISTRO")
public class Registro implements Serializable {

    @Column(name = "RE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RE_MATRICULA", nullable = true)
    private String matricula;

    @Column(name = "RE_REFEICAO", nullable = true)
    private String refeicao;

    @Column(name = "RE_DATA", nullable = true)
    private Date data;

    @Column(name = "RE_DIA_SEMANA", nullable = true)
    private String diaSemana;

    @Column(name = "RE")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(String refeicao) {
        this.refeicao = refeicao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Registro() {

    }

}