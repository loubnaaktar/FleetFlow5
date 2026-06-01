package org.example.fleetflow.service.implementations;

import org.example.fleetflow.DTO.ClientDTO;
import org.example.fleetflow.Repository.ClientRepository;
import org.example.fleetflow.Repository.LivraisonRepository;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.service.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LivraisonRepository livraisonRepository;
    private final ClientMapper mapper;


    public ClientServiceImpl(ClientRepository clientRepository, LivraisonRepository livraisonRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.livraisonRepository = livraisonRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        return mapper.toDTO(clientRepository.save(mapper.toEntity(clientDTO)));
    }

    @Override
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll().stream().map(mapper::toDTO).toList();
    }


    @Override
    public ClientDTO Modifierclient(Long id,ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Client " + id + " n'existe pas"));
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        client.setVille(clientDTO.getVille());
        client.setTelephone(clientDTO.getTelephone());
        return mapper.toDTO(clientRepository.save(client));
    }


    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO addClientNotExist(ClientDTO clientDTO) {

        Client client = clientRepository.findByEmail(clientDTO.getEmail());

        if (client != null) {
            throw new RuntimeException("Email déjà existant");
        }

        return mapper.toDTO(clientRepository.save(mapper.toEntity(clientDTO)));
    }
}







