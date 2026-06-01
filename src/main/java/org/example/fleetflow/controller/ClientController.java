package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import org.example.fleetflow.DTO.ClientDTO;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping
    public void AjouterClient(@Valid @RequestBody ClientDTO client){
        clientService.addClient(client);
    }
    @GetMapping
    public List<ClientDTO> AfficherClients(){
        return clientService.getAllClients();
    }
    @PutMapping("/{id}")
    public void ModifierClient(@Valid @PathVariable Long id,ClientDTO clientDTO){
        clientService.Modifierclient(id,clientDTO);
    }
    @DeleteMapping("/{id}")
    public void DeleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
