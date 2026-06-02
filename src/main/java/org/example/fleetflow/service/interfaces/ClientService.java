package org.example.fleetflow.service.interfaces;

import org.example.fleetflow.DTO.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    ClientDTO addClient(ClientDTO clientDTO);
    Page<ClientDTO> getAllClients(Pageable pageable);
    ClientDTO Modifierclient(Long id,ClientDTO clientDTO);
    void deleteClient(Long id);
    ClientDTO addClientNotExist(ClientDTO clientDTO);

}
