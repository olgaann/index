package com.example.springBootApp.services;

import com.example.springBootApp.entities.Client;
import com.example.springBootApp.entities.DTO.ClientDTO;
import com.example.springBootApp.mappers.ClientMapper;
import com.example.springBootApp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    public ClientDTO findById(long id) {
        return clientMapper.mapClientToClientDTO(clientRepository.findById(id).get());
    }

    public List<ClientDTO> findAll() {
        return clientMapper.mapListClientToListClientDTO(clientRepository.findAll());
    }
}
