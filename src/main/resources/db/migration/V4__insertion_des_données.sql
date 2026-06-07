INSERT INTO client (id, nom, email, ville, telephone)
VALUES
    (1, 'Ali', 'ali@gmail.com', 'Casablanca', '0600000001'),
    (2, 'Sara', 'sara@gmail.com', 'Rabat', '0600000002');


INSERT INTO vehicule (id, matricule, type, capacite, statut)
VALUES
    (1, '123-A-456', 'CAMION', 1000, 'DISPONIBLE'),
    (2, '789-B-999', 'CAMIONETTE', 500, 'DISPONIBLE');

/*
INSERT INTO livraison (
    date_livraison,
    adresse_depart,
    adresse_destination,
    statut,
    chauffeur_id,
    client_id,
    vehicule_id
)
VALUES
    ('2026-06-03 08:00:00', 'Casablanca', 'Rabat', 'ENATTENTE', 1, 1, 1),
    ('2026-06-03 09:30:00', 'Rabat', 'Kenitra', 'LIVREE', 1, 1, 2),
    ('2026-06-03 11:00:00', 'Fes', 'Meknes', 'ENATTENTE', 1, 2, 1),
    ('2026-06-04 10:00:00', 'Marrakech', 'Agadir', 'ENCOURS', 2, 1, 2),
    ('2026-06-04 12:30:00', 'Agadir', 'Essaouira', 'LIVREE', 2, 2, 1),
    ('2026-06-04 15:00:00', 'Tanger', 'Tetouan', 'ENATTENTE', 2, 1, 2);
*/
