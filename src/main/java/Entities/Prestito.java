package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")  // Nome della tabella nel database
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //relazione di molti ad uno con utente, in quanto un utente può prendere molti libri in prestito ma
    //ogni operazione di prestito è associata ad uno utente
    @ManyToOne
    @JoinColumn(name = "utente_id") // prende utente dal db
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_id")  // prende elemento dal db
    private Elemento elementoPrestato;

    @Column(name = "data_inizio_prestito", nullable = false)   //non può essere nulla
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    // Costruttore vuoto
    public Prestito() {
    }

    // Costruttore
    public Prestito(Utente utente, Elemento elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);  // Calcola in automatico la data di restituzione
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Elemento getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Elemento elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);  // Aggiorna la data di restituzione prevista
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    // Metodo toString (utile per debug)
    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente.getNome() + " " + utente.getCognome() +
                ", elementoPrestato=" + elementoPrestato.getTitolo() +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}