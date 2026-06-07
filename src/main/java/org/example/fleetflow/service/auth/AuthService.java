package org.example.fleetflow.service.auth;

import lombok.RequiredArgsConstructor;

import org.example.fleetflow.DTO.auth.AuthRequest;
import org.example.fleetflow.DTO.auth.RegisterRequest;
import org.example.fleetflow.Repository.UtilisateurRepository;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Utilisateur;
import org.example.fleetflow.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UtilisateurRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        Utilisateur user;
        if (request.getRole() == Utilisateur.Role.CHAUFFEUR) {
            Chauffeur chauffeur = new Chauffeur();
            chauffeur.setNom(request.getNom());
            chauffeur.setTelephone(request.getTelephone());
            chauffeur.setPermisType(request.getPermisType());
            chauffeur.setDisponible(true);
            user = chauffeur;
        } else {
            user = new Utilisateur();
        }
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repo.save(user);
        return jwtService.generateToken(user.getEmail(),user);
    }
    public String authenticate(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Utilisateur user =
                repo.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("utilisateur introuvable");
        }
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("mot de passe incorrect");
        }
        return jwtService.generateToken(user.getEmail(),user);
    }
}
