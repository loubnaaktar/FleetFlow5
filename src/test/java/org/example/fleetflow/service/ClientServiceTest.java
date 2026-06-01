package org.example.fleetflow.service;

import org.example.fleetflow.DTO.ClientDTO;
import org.example.fleetflow.Repository.ClientRepository;
import org.example.fleetflow.Repository.LivraisonRepository;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @Mock
    LivraisonRepository livraisonRepository;

    @Mock
    ClientMapper mapper;

    @InjectMocks
    ClientService clientService;

    @Test
    void addClientTest() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail("test@gmail.com");

        Client client = new Client();
        client.setEmail("test@gmail.com");

        Mockito.when(mapper.toEntity(clientDTO)).thenReturn(client);

        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        Mockito.when(mapper.toDTO(client)).thenReturn(clientDTO);

        ClientDTO result = clientService.addClient(clientDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("test@gmail.com", result.getEmail());


    }

    @Test
    void addClientExistTest() {

        Client existclient = new Client(1L," noura","noura@gmail.com","bm","06878909");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail("noura@gmail.com");
        Mockito.when(clientRepository.findByEmail(existclient.getEmail())).thenReturn(existclient);


        Assertions.assertThrows(RuntimeException.class,()->clientService.addClientNotExist(clientDTO));


    }
}