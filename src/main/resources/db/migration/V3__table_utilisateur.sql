ALTER TABLE livraison DROP FOREIGN KEY fk_chauffeur;

CREATE TABLE IF NOT EXISTS utilisateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','MANAGER','CHAUFFEUR')
    );

ALTER TABLE chauffeur
    ADD COLUMN utilisateur_id BIGINT;

ALTER TABLE chauffeur
    ADD CONSTRAINT fk_chauffeur_utilisateur
        FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
            ON DELETE CASCADE;


ALTER TABLE livraison
    ADD CONSTRAINT fk_chauffeur
        FOREIGN KEY (chauffeur_id) REFERENCES chauffeur(id);