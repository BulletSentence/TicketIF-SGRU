package br.edu.ifma.ticketif.model.DAO;

import br.edu.ifma.ticketif.core.EntityManagerSource;
import br.edu.ifma.ticketif.model.entity.database.Aluno;
import br.edu.ifma.ticketif.model.entity.database.Autorizacao;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("ALL")
public class AutorizacaoDAO {

    private EntityManager entidadeGerenciamento;
    public AutorizacaoDAO(){
        this.entidadeGerenciamento = EntityManagerSource.getEntityManager();
    }

    public Autorizacao getByCod(int codAutorizacao) {
        return entidadeGerenciamento.find(Autorizacao.class, codAutorizacao);
    }

    public List<Autorizacao> obterListaAluno() {

        entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
        List<Autorizacao> listaAutorizacao = entidadeGerenciamento.createQuery("select u from Autorizacao u", Autorizacao.class).getResultList();
        entidadeGerenciamento.getTransaction().commit();
        return listaAutorizacao;
    }

    public Boolean limparCache(){
        entidadeGerenciamento.createQuery("delete from db_autorizacao where au_id != (select autorizacao from db_aluno)").getResultList();
        return true;
    }


    public Autorizacao buscarAutorizacao (Autorizacao autorizacao){
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            autorizacao = entidadeGerenciamento.find(Autorizacao.class, autorizacao.getId());
            entidadeGerenciamento.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de err
        }
        return autorizacao;
    }

    public boolean inserirAutorizacao(Autorizacao autorizacao) {

        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.persist(autorizacao);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;

        } catch (Exception ex) {

            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }


    public boolean atualizarAutorizacao(Autorizacao autorizacao) {
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.merge(autorizacao);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }

    public boolean removerAutorizacao(Autorizacao autorizacao) {
        try {
            entidadeGerenciamento.getTransaction().begin();
            autorizacao = entidadeGerenciamento.find(Autorizacao.class, autorizacao.getId());
            entidadeGerenciamento.remove(autorizacao);
            entidadeGerenciamento.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();
            return false;
        }
    }



}





