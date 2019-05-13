package br.edu.ifma.ticketif.model.entity.database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity

@Table (name = "DB_AUTORIZACAO")
public class Autorizacao implements Serializable {

    @Column(name = "AU_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AU_DOMINGO", nullable = true)
    private Boolean dom;

    @Column(name = "AU_SEGUNDA", nullable = true)
    private Boolean seg;

    @Column(name = "AU_TERCA", nullable = true)
    private Boolean ter;

    @Column(name = "AU_QUARTA", nullable = true)
    private Boolean qua;

    @Column(name = "AU_QUINTA", nullable = true)
    private Boolean qui;

    @Column(name = "AU_SEXTA", nullable = true)
    private Boolean sex;

    @Column(name = "AU_SABADO", nullable = true)
    private Boolean sab;

    @Column(name = "AU_DATA")
    private Date data;

    public Boolean getDom() {
        return dom;
    }

    public void setDom(Boolean dom) {
        this.dom = dom;
    }

    public Boolean getSeg() {
        return seg;
    }

    public void setSeg(Boolean seg) {
        this.seg = seg;
    }

    public Boolean getTer() {
        return ter;
    }

    public void setTer(Boolean ter) {
        this.ter = ter;
    }

    public Boolean getQua() {
        return qua;
    }

    public void setQua(Boolean qua) {
        this.qua = qua;
    }

    public Boolean getQui() {
        return qui;
    }

    public void setQui(Boolean qui) {
        this.qui = qui;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getSab() {
        return sab;
    }

    public void setSab(Boolean sab) {
        this.sab = sab;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Autorizacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}