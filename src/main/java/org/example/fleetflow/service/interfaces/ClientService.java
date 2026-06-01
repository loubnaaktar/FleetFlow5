package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO addClient(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    ClientDTO Modifierclient(Long id,ClientDTO clientDTO);
    void deleteClient(Long id);
    ClientDTO addClientNotExist(ClientDTO clientDTO);

}
