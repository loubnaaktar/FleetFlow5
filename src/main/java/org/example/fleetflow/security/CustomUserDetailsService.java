package org.example.fleetflow.security;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow.Repository.UtilisateurRepository;
import org.example.fleetflow.model.Utilisateur;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UtilisateurRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("utilisateur introuvable");
        }
        return user;
    }
}
