package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utenti")  // Nome della tabella nel database
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Nome non nullo
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(name = "numero_tessera", unique = true, nullable = false)  // Numero tessera unico e non nullo
    private String numeroTessera;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestito> prestiti;

    // Costruttore vuoto
    public Utente() {
    }

    // Costruttore
    public Utente(String nome, String cognome, LocalDate dataNascita, String numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }


    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", numeroTessera='" + numeroTessera + '\'' +
                '}';
    }
}