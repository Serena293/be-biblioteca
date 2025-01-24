package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")  // Nome della tabella nel database
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)  // Chiave esterna per l'utente
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_id", nullable = false)  // Chiave esterna per l'elemento prestato
    private Elemento elementoPrestato;

    @Column(name = "data_inizio_prestito", nullable = false)  // Data di inizio prestito (non nulla)
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)  // Data di restituzione prevista (non nulla)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")  // Data di restituzione effettiva (può essere nulla)
    private LocalDate dataRestituzioneEffettiva;

    // Costruttore vuoto (richiesto da JPA)
    public Prestito() {
    }

    // Costruttore con parametri (opzionale, ma utile)
    public Prestito(Utente utente, Elemento elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);  // Calcola la data di restituzione prevista
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