package com.example.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Integer capacite;

    @OneToMany(mappedBy = "salle",
            cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "salle_equipement",
            joinColumns = @JoinColumn(name = "salle_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private Set<Equipement> equipements = new HashSet<>();

    public Salle() {}



    public Salle(String nom, Integer capacite) {
        this.nom = nom;
        this.capacite = capacite;

    }

    public void addReservation(Reservation r) {
        reservations.add(r);
        r.setSalle(this);
    }

    public boolean estDisponible(LocalDateTime debut,
                                 LocalDateTime fin) {

        for (Reservation r : reservations) {

            if (!(fin.isBefore(r.getDateDebut())
                    || debut.isAfter(r.getDateFin()))) {

                return false;
            }
        }
        return true;

    }
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public Long getId() { return id; }
    public String getNom() { return nom; }
}