package com.example.springBootApp.mappers;

import com.example.springBootApp.entities.Client;
import com.example.springBootApp.entities.DTO.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO mapClientToClientDTO(Client client);
    List<ClientDTO> mapListClientToListClientDTO(List<Client> clientList);
}
