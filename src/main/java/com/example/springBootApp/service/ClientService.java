package com.example.springBootApp.service;


import com.example.springBootApp.controller.dto.request.ClientCreationRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.mapper.ClientMapper;
import com.example.springBootApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientInfoResponseDTO findById(long id) {
        return clientMapper.mapClientToClientDTO(clientRepository.findById(id).get());
    }

    public List<ClientInfoResponseDTO> findAll() {
        return clientMapper.mapListClientToListClientDTO(clientRepository.findAll());
    }

    public void createClient(ClientCreationRequestDTO clientCreationRequestDTO) {
        Client client = clientMapper.mapClientToClientDTO(clientCreationRequestDTO);
        clientRepository.save(client);
    }
}
