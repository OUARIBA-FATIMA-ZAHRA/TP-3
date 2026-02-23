package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nom;

    @NotBlank
    @Column(nullable = false)
    private String prenom;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String telephone;

    private String adresse;

    @OneToMany(mappedBy = "utilisateur",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Utilisateur() {}

    public Utilisateur(String nom, String prenom,
                       String email,
                       String telephone,
                       String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public void addReservation(Reservation r) {
        reservations.add(r);
        r.setUtilisateur(this);
    }

    public void removeReservation(Reservation r) {
        reservations.remove(r);
        r.setUtilisateur(null);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getAdresse() { return adresse; }
    public List<Reservation> getReservations() { return reservations; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}