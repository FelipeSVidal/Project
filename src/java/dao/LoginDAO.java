/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Cliente;
import modelo.Login;
import modelo.Usuario;

/**
 *
 * @author felipe
 */
public class LoginDAO {
    private Login login ;
    
    public LoginDAO(){
        
    }
    //------------------- Metodos do Usuário ------------------------------------
    public boolean adicionar(Login login){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MarcaiPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(login);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
        return true;
    }
    public boolean atualizar(Login login){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MarcaiPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.refresh(login);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
        return true;
    }
    
    // -------------------------- Metodos do Sistema ----------------------------
    public Login buscar(long id){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MarcaiPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        this.login = manager.find(Login.class, 1);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
        return this.login.clone();
    }
}
