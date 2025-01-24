package DAO;

import Entities.Rivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class RivistaDAO {
    private final EntityManager em;

    public RivistaDAO(EntityManager em) {
        this.em = em;
    }

    //create
    public void save(Rivista rivista) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(rivista);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //read
    public Optional<Rivista> findById(Long id) {
        Rivista rivista = em.find(Rivista.class, id);
        return Optional.ofNullable(rivista);
    }

    //read
    public List<Rivista> findAll() {
        return em.createQuery("SELECT r FROM Rivista r", Rivista.class).getResultList();
    }

    //update
    public void update(Rivista rivista) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(rivista);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //delete
    public void delete(Rivista rivista) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(rivista));  // Assicura che l'istanza sia gestita
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Metodo specifico per cercare riviste per periodicità
    public List<Rivista> findByPeriodicita(Rivista.Periodicita periodicita) {
        return em.createQuery("SELECT r FROM Rivista r WHERE r.periodicita = :periodicita", Rivista.class)
                .setParameter("periodicita", periodicita)
                .getResultList();
    }
}