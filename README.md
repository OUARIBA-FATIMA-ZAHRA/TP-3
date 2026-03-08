# TP 3 : Lier Salle–Réservation–Utilisateur, ajouter Équipement (ManyToMany), expérimenter cascade et suppression orpheline
<img width="1870" height="898" alt="Capture d&#39;écran 2026-02-23 200510" src="https://github.com/user-attachments/assets/6c5dc9eb-0903-4c35-8197-d44449694389" />
<img width="1862" height="938" alt="Capture d&#39;écran 2026-02-23 200656" src="https://github.com/user-attachments/assets/76f57def-30d7-4aaf-abb9-ecc858ce6061" />
<img width="1864" height="902" alt="Capture d&#39;écran 2026-02-23 200833" src="https://github.com/user-attachments/assets/0e1785b3-b9bb-49e9-b438-a7a8848a621f" />
<img width="1833" height="901" alt="Capture d&#39;écran 2026-02-23 200859" src="https://github.com/user-attachments/assets/d3c58a2d-73d9-4810-bb27-f176cebcb266" />
<img width="1869" height="931" alt="Capture d&#39;écran 2026-02-23 200934" src="https://github.com/user-attachments/assets/341039a0-d9f6-481b-84ef-e8d0f19129d0" />
<img width="1869" height="916" alt="Capture d&#39;écran 2026-02-23 201002" src="https://github.com/user-attachments/assets/3c0c5e4d-0bf8-49dd-87bf-19e61287de96" />
<img width="1028" height="883" alt="Capture d&#39;écran 2026-02-23 203218" src="https://github.com/user-attachments/assets/75e6038a-9ced-4038-af82-339b98ed91d4" />

# Analyse du diagramme de classes:

1-Relation Utilisateur – Reservation

Relation OneToMany / ManyToOne

Un utilisateur peut avoir plusieurs réservations

Une réservation appartient à un seul utilisateur

2- Relation Salle – Reservation

Relation OneToMany / ManyToOne

Une salle peut avoir plusieurs réservations

Une réservation concerne une seule salle

3- Relation Salle – Equipement

Relation ManyToMany

Une salle peut avoir plusieurs équipements

Un équipement peut appartenir à plusieurs salles

4- Relation Salle – Categorie

Relation ManyToOne

Plusieurs salles peuvent appartenir à une même catégorie

Une salle appartient à une seule catégorie

# Analyse des annotations utilisées:

@Entity :Indique que la classe est une entité JPA.
Elle sera automatiquement transformée en table dans la base de données.

@Id :Définit la clé primaire de l’entité.
Chaque objet aura un identifiant unique en base.

@GeneratedValue(strategy = GenerationType.IDENTITY) :Permet de générer automatiquement la valeur de la clé primaire (auto-incrément).

@OneToMany :Représente une relation un à plusieurs .


@ManyToOne :Représente une relation plusieurs à un .


@ManyToMany :Représente une relation plusieurs à plusieurs .

