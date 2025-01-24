package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Rivista extends Elemento {

    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        SEMESTRALE
    }

    @Enumerated(EnumType.STRING)  // Trasforma il tipo enum in una stringa nel database
    private Periodicita periodicita;

    // Costruttore vuoto (richiesto da JPA)
    public Rivista() {
    }

    // Costruttore
    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    // Getter e Setter
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }


    @Override
    public String toString() {
        return "Rivista{" +
                ", codiceISBN='" + getCodiceISBN() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", periodicità=" + periodicita +
                '}';
    }
}