package com.example.springBootApp.service;


import com.example.springBootApp.controller.dto.request.ClientRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.exception.ClientNotFoundException;
import com.example.springBootApp.mapper.ClientMapper;
import com.example.springBootApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ClientInfoResponseDTO createClient(ClientRequestDTO requestDTO) {
        Client client = clientMapper.mapClientToClientDTO(requestDTO);
        client = clientRepository.save(client);
        return clientMapper.mapClientToClientDTO(client);
    }

    public ClientInfoResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new ClientNotFoundException(id);
        Client client = optionalClient.get();
        client.setName(clientRequestDTO.getName());
        client.setPhone(clientRequestDTO.getPhone());
        client = clientRepository.save(client);
        return clientMapper.mapClientToClientDTO(client);
    }

    public void deleteClient(Long id) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new ClientNotFoundException(id);
        clientRepository.deleteById(id);
    }
}
