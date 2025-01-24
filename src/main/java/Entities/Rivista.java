package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Rivista extends Elemento {
    // Enumerazione per la periodicità
    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        SEMESTRALE
    }

    @Enumerated(EnumType.STRING)  // Mappa l'enum come una stringa nel database
    private Periodicita periodicita;

    // Costruttore vuoto (richiesto da JPA)
    public Rivista() {
    }

    // Costruttore con parametri (opzionale, ma utile)
    public Rivista(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine); // Chiamata al costruttore della superclasse
        this.periodicita = periodicita;
    }

    // Getter e Setter per periodicita
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }


    @Override
    public String toString() {
        return "Rivista{" +
                "id=" + getId() +
                ", codiceISBN='" + getCodiceISBN() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", periodicita=" + periodicita +
                '}';
    }
}