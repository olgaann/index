package com.example.springBootApp.mapper;


import com.example.springBootApp.controller.dto.request.ClientCreationRequestDTO;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientInfoResponseDTO mapClientToClientDTO(Client client);
    List<ClientInfoResponseDTO> mapListClientToListClientDTO(List<Client> clientList);

    Client mapClientToClientDTO(ClientCreationRequestDTO clientDTO);
}
