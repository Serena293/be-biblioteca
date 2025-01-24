package DAO;

import Entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class LibroDAO {
    private final EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    //create
    public void save(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //read
    public Optional<Libro> findById(Long id) {
        Libro libro = em.find(Libro.class, id);
        return Optional.ofNullable(libro);
    }

    //read
    public List<Libro> findAll() {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    //update
    public void update(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //delete
    public void delete(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(libro));  // Assicura che l'istanza sia gestita
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Metodo specifico per cercare libri per autore
    public List<Libro> findByAutore(String autore) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }
}