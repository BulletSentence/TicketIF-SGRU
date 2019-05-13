package br.edu.ifma.ticketif.model.entity.database;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity

@Table (name = "AL_AUTORIZACAO")
public class Autorizacao implements Serializable {

    @Column(name = "AU_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AU_DESCRICAO")
    private String descricao;

    public Autorizacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}