/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifma.ticketif.core;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSource {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaDAO");

    @Produces @RequestScoped
    public static EntityManager getEntityManager(){
        System.out.println("Banco de Dados: Conectado");
        return emf.createEntityManager();
    }
    
}
