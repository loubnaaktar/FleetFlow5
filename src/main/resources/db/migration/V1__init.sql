CREATE TABLE vehicule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          matricule VARCHAR(50) NOT NULL UNIQUE,
                          type VARCHAR(50) NOT NULL,
                          capacite INT NOT NULL,
                          statut VARCHAR(50) NOT NULL
);

CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(100) NOT NULL,
                        ville VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL UNIQUE,
                        telephone VARCHAR(20)
);

CREATE TABLE chauffeur (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nom VARCHAR(100) NOT NULL,
                           telephone VARCHAR(20),
                           permis_type VARCHAR(50),
                           disponible BOOLEAN
);

CREATE TABLE livraison (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           date_livraison DATETIME,
                           adresse_depart VARCHAR(255) NOT NULL,
                           adresse_destination VARCHAR(255) NOT NULL,
                           statut ENUM('ENATTENTE','ENCOURS','LIVREE') NOT NULL,
                           chauffeur_id BIGINT,
                           client_id BIGINT,
                            vehicule_id BIGINT
);