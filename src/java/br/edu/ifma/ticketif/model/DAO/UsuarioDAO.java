/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifma.ticketif.model.DAO;

import br.edu.ifma.ticketif.core.EntityManagerSource;
import br.edu.ifma.ticketif.model.entity.User.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@SuppressWarnings("ALL")
public class UsuarioDAO {

    private EntityManager entidadeGerenciamento;


    public UsuarioDAO(){
        this.entidadeGerenciamento = EntityManagerSource.getEntityManager();
    }

    public Usuario getByCod(int codUsuario) {

        return entidadeGerenciamento.find(Usuario.class, codUsuario);
    }

    public List<Usuario> obterLista() {

        entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
        List<Usuario> lista = entidadeGerenciamento.createQuery("select u from Usuario u").getResultList();
        entidadeGerenciamento.getTransaction().commit();
        return lista;
    }

    public boolean inserir(Usuario usuario) {
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.persist(usuario);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }

    public boolean usuarioExistente(Usuario usuario) {
        try {
            usuario = (Usuario) entidadeGerenciamento.createQuery("SELECT u FROM Usuario u WHERE u.login LIKE :login").setParameter("login", "%" + usuario.getLogin() + "%").getSingleResult();
            return true; // Se encontrou registro, então OK!
        } catch (NoResultException ex) {
           return false; // Caso não exista registro
        }
    }

    public boolean atualizar(Usuario usuario) {
        try {
            entidadeGerenciamento.getTransaction().begin();   //Inicia a negociação da persistencia
            entidadeGerenciamento.merge(usuario);
            entidadeGerenciamento.getTransaction().commit();  //Encerra a negociação da persistencia
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();    //Desfaz alterações, em caso de erro
            return false;
        }
    }

    public boolean remover(Usuario usuario) {
        try {
            entidadeGerenciamento.getTransaction().begin();
            usuario = entidadeGerenciamento.find(Usuario.class, usuario.getId());
            entidadeGerenciamento.remove(usuario);
            entidadeGerenciamento.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entidadeGerenciamento.getTransaction().rollback();
            return false;
        }
    }

    public void removerPorId(final int codUsuario) {
        try {
            Usuario usuario = getByCod(codUsuario);
            remover(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
