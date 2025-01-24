package DAO;

import Entities.Elemento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ElementoDAO {
    private final EntityManager em;  //per gestire l'entità

    //costruttore
    public ElementoDAO(EntityManager em) {
        this.em = em;
    }

    //create
    public void save(Elemento elemento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(elemento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
            throw e;
        }
    }

    //read
    public Elemento findById(Long id) {
        return em.find(Elemento.class, id);
    }


    //read
    public List<Elemento> findAll() {
        return em.createQuery("SELECT e FROM Elemento e", Elemento.class).getResultList();
    }

    //update
    public void update(Elemento elemento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(elemento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //delete
    public void delete(Elemento elemento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(elemento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            }
        finally {
            em.close();
        }
}}