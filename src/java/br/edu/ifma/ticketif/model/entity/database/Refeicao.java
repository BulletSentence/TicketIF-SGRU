package br.edu.ifma.ticketif.model.entity.database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table (name = "DB_REFEICAO")
public class Refeicao implements Serializable {

    @Column(name = "AL_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RE_CAFE")
    private Boolean cafe;

    @Column(name = "RE_ALMOCO")
    private Boolean almoco;

    @Column(name = "RE_JANTAR")
    private Boolean jantar;

    @Column(name = "RE_CEIA")
    private Boolean ceia;

    public Refeicao() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Refeicao outroAluno = (Refeicao) obj;
        if (id == null) {
            if (outroAluno.id != null)
                return false;
        } else if (!id.equals(outroAluno.id))
            return false;
        return true;
    }
}