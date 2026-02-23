package com.example;

import com.example.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("gestion-reservations");

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean quitter = false;

        while (!quitter) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Ajouter utilisateur");
            System.out.println("2. Ajouter salle");
            System.out.println("3. Créer réservation");
            System.out.println("4. Quitter");

            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {

                case 1:
                    ajouterUtilisateur(sc);
                    break;

                case 2:
                    ajouterSalle(sc);
                    break;

                case 3:
                    creerReservation(sc);
                    break;

                case 4:
                    quitter = true;
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        }

        emf.close();
        sc.close();
        System.out.println("Programme terminé.");
    }


    private static void ajouterUtilisateur(Scanner sc) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.print("Nom : ");
        String nom = sc.nextLine();

        System.out.print("Prenom : ");
        String prenom = sc.nextLine();

        System.out.print("Email : ");
        String email = sc.nextLine();

        Utilisateur u = new Utilisateur(
                nom,
                prenom,
                email,
                "0600000000",
                "Maroc"
        );

        em.persist(u);

        em.getTransaction().commit();
        em.close();

        System.out.println("Utilisateur ajouté !");
    }


    private static void ajouterSalle(Scanner sc) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.print("Nom salle : ");
        String nom = sc.nextLine();

        System.out.print("Capacité : ");
        int capacite = sc.nextInt();
        sc.nextLine();

        Salle s = new Salle(nom, capacite);

        em.persist(s);

        em.getTransaction().commit();
        em.close();

        System.out.println("Salle ajoutée !");
    }


    private static void creerReservation(Scanner sc) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.print("ID utilisateur : ");
        Long userId = sc.nextLong();

        System.out.print("ID salle : ");
        Long salleId = sc.nextLong();
        sc.nextLine();

        Utilisateur u = em.find(Utilisateur.class, userId);
        Salle s = em.find(Salle.class, salleId);

        if (u == null || s == null) {
            System.out.println("Utilisateur ou Salle introuvable !");
            em.getTransaction().rollback();
            em.close();
            return;
        }

        LocalDateTime debut = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = debut.plusHours(2);

        if (!s.estDisponible(debut, fin)) {
            System.out.println("Salle non disponible !");
            em.getTransaction().rollback();
            em.close();
            return;
        }

        Reservation r = new Reservation(debut, fin, "Console");

        u.addReservation(r);
        s.addReservation(r);

        em.persist(r);

        em.getTransaction().commit();
        em.close();

        System.out.println("Réservation créée !");
    }
}