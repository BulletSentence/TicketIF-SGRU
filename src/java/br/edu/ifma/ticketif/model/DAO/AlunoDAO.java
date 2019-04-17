package br.edu.ifma.ticketif.model.DAO;

import br.edu.ifma.ticketif.core.EntityManagerSource;
import br.edu.ifma.ticketif.model.entity.database.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@SuppressWarnings("ALL")
public class AlunoDAO {

    private EntityManager entidadeGerenciamento;
    public AlunoDAO(){
        this.entidadeGerenciamento = EntityManagerSource.getEntityManager();
    }

    public Aluno getByCod(int codAluno) {
        return entidadeGerenciamento.find(Aluno.class, codAluno);
    }

    public List<Aluno> obterListaAluno() {

        entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
        List<Aluno> listaAluno = entidadeGerenciamento.createQuery("select u from Aluno u", Aluno.class).getResultList();
        entidadeGerenciamento.getTransaction().commit();
        return listaAluno;
    }


    public Aluno buscarAluno (String nome){
        Aluno aluno = null;
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.find(Aluno.class, nome);
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de err
        }
        return aluno;
    }

    public boolean inserirAluno(Aluno aluno) {
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.persist(aluno);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }

    public boolean usuarioExistente(Aluno aluno) {
        try {
            aluno = (Aluno) entidadeGerenciamento.createQuery("SELECT u FROM Aluno u WHERE u.nome LIKE :nome").setParameter("nome", "%" + aluno.getNome() + "%").getSingleResult();
            return true; // Se encontrou registro, então OK!
        } catch (NoResultException ex) {
            return false; // Caso não exista registro
        }
    }

    public boolean atualizarAluno(Aluno aluno) {
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia

            entidadeGerenciamento.merge(aluno);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }

    public boolean removerAluno(Aluno aluno) {
        try {
            entidadeGerenciamento.getTransaction().begin();
            aluno = entidadeGerenciamento.find(Aluno.class, aluno.getId());
            entidadeGerenciamento.remove(aluno);
            entidadeGerenciamento.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();
            return false;
        }
    }


}





