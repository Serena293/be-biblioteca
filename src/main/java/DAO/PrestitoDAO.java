package DAO;

import Entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    //create
    public void save(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(prestito);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //read
    public Optional<Prestito> findById(Long id) {
        Prestito prestito = em.find(Prestito.class, id);
        return Optional.ofNullable(prestito);
    }

    //read
    public List<Prestito> findAll() {
        return em.createQuery("SELECT p FROM Prestito p", Prestito.class).getResultList();
    }

    //update
    public void update(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(prestito);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //delete
    public void delete(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(prestito));  // Assicura che l'istanza sia gestita
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Metodo specifico per cercare prestiti di un utente
    public List<Prestito> findByUtente(Long utenteId) {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.utente.id = :utenteId", Prestito.class)
                .setParameter("utenteId", utenteId)
                .getResultList();
    }

    // Metodo specifico per cercare prestiti scaduti e non restituiti
    public List<Prestito> findPrestitiScadutiNonRestituiti() {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class)
                .getResultList();
    }
}