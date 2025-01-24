package DAO;

import Entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    //create
    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //read
    public Optional<Utente> findById(Long id) {
        Utente utente = em.find(Utente.class, id);
        return Optional.ofNullable(utente);
    }

    //read
    public List<Utente> findAll() {
        return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
    }

    //update
    public void update(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //delete
    public void delete(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(utente));  // Assicura che l'istanza sia gestita
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Metodo specifico per cercare utenti per numero di tessera
    public Optional<Utente> findByNumeroTessera(String numeroTessera) {
        return em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera", Utente.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultStream()
                .findFirst();
    }
}