package org;

import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Ottieni un'istanza di EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        EntityManager em = emf.createEntityManager();

        // Crea i DAO
        ElementoDAO elementoDAO = new ElementoDAO(em);
        LibroDAO libroDAO = new LibroDAO(em);
        RivistaDAO rivistaDAO = new RivistaDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // Crea un'istanza di Utente
        Utente utente = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 15), "T12345");

        // Crea un'istanza di Libro
        Libro libro = new Libro("978-8804668237", "Il Signore degli Anelli", 1954, 1216, "J.R.R. Tolkien", "Fantasy");

        // Crea un'istanza di Rivista
        Rivista rivista = new Rivista("977-1123456801", "National Geographic", 2023, 100, Rivista.Periodicita.MENSILE);

        // Salva utente, libro e rivista nel database
        utenteDAO.save(utente);
        libroDAO.save(libro);
        rivistaDAO.save(rivista);

        // Crea un'istanza di Prestito per il libro
        Prestito prestitoLibro = new Prestito(utente, libro, LocalDate.now().minusDays(10)); // Prestito iniziato 10 giorni fa
        prestitoDAO.save(prestitoLibro);

        // Crea un'istanza di Prestito per la rivista
        Prestito prestitoRivista = new Prestito(utente, rivista, LocalDate.now().minusDays(5)); // Prestito iniziato 5 giorni fa
        prestitoDAO.save(prestitoRivista);

        // Trova e stampa tutti i libri
        System.out.println("Tutti i libri:");
        libroDAO.findAll().forEach(l -> System.out.println(l.getTitolo() + " di " + l.getAutore()));

        // Trova e stampa tutte le riviste
        System.out.println("\nTutte le riviste:");
        rivistaDAO.findAll().forEach(r -> System.out.println(r.getTitolo() + " (" + r.getPeriodicita() + ")"));

        // Trova e stampa tutti i prestiti dell'utente
        System.out.println("\nPrestiti di Mario Rossi:");
        prestitoDAO.findByUtente(utente.getId()).forEach(p -> System.out.println(
                "Elemento: " + p.getElementoPrestato().getTitolo() +
                        ", Data inizio: " + p.getDataInizioPrestito() +
                        ", Data restituzione prevista: " + p.getDataRestituzionePrevista()
        ));

        // Trova e stampa i prestiti scaduti e non restituiti
        System.out.println("\nPrestiti scaduti e non restituiti:");
        prestitoDAO.findPrestitiScadutiNonRestituiti().forEach(p -> System.out.println(
                "Elemento: " + p.getElementoPrestato().getTitolo() +
                        ", Data restituzione prevista: " + p.getDataRestituzionePrevista()
        ));

        // Chiudi EntityManager e EntityManagerFactory
        em.close();
        emf.close();
    }
}