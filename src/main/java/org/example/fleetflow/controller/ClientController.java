package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import org.example.fleetflow.DTO.ClientDTO;
import org.example.fleetflow.service.implementations.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientServiceImpl clientService;
    @PostMapping
    public void AjouterClient(@Valid @RequestBody ClientDTO client){
        clientService.addClient(client);
    }
    @GetMapping
    public Page<ClientDTO> AfficherClients(@PageableDefault(sort = "nom",
    direction = Sort.Direction.ASC) Pageable pageable){
        return clientService.getAllClients(pageable);
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
