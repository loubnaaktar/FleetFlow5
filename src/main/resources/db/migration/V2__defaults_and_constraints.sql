ALTER TABLE chauffeur
    MODIFY disponible BOOLEAN DEFAULT TRUE;

ALTER TABLE livraison
    MODIFY statut ENUM('ENATTENTE','ENCOURS','LIVREE') DEFAULT 'ENATTENTE';

ALTER TABLE vehicule
    ADD CONSTRAINT chk_capacite CHECK (capacite > 0);

ALTER TABLE livraison
    ADD CONSTRAINT fk_chauffeur FOREIGN KEY (chauffeur_id) REFERENCES chauffeur(id),
    ADD CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id),
    ADD CONSTRAINT fk_vehicule FOREIGN KEY (vehicule_id) REFERENCES vehicule(id);