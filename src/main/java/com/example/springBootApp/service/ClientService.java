package com.example.springBootApp.service;

import com.example.springBootApp.controller.dto.request.ClientRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientAndRoomsInfoResponseDTO;
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

    public Client findClientById(long id) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new ClientNotFoundException(id);
        return optionalClient.get();
    }
    public ClientInfoResponseDTO findById(long id) throws ClientNotFoundException {
        return clientMapper.mapClientToClientInfoDTO(findClientById(id));
    }

    public List<ClientInfoResponseDTO> findAll() {
        return clientMapper.mapListClientToListClientDTO(clientRepository.findAll());
    }

    public ClientInfoResponseDTO createClient(ClientRequestDTO requestDTO) {
        Client client = clientMapper.mapClientToClientDTO(requestDTO);
        client = clientRepository.save(client);
        return clientMapper.mapClientToClientInfoDTO(client);
    }

    public ClientInfoResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) throws ClientNotFoundException {
        Client client = findClientById(id);
        client.setName(clientRequestDTO.getName());
        client.setPhone(clientRequestDTO.getPhone());
        client = clientRepository.save(client);
        return clientMapper.mapClientToClientInfoDTO(client);
    }

    public void deleteClient(Long id) throws ClientNotFoundException {
        findClientById(id);
        clientRepository.deleteById(id);
    }

    public ClientAndRoomsInfoResponseDTO findClientAndRoomsById(Long id) throws ClientNotFoundException{
        return clientMapper.mapClientToClientAndRoomsInfoDTO(findClientById(id));
    }
}
