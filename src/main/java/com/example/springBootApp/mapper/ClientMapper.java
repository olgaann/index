package com.example.springBootApp.mapper;

import com.example.springBootApp.controller.dto.request.ClientRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientAndRoomsInfoResponseDTO;
import com.example.springBootApp.entity.Booking;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientInfoResponseDTO mapClientToClientInfoDTO(Client client);

    List<ClientInfoResponseDTO> mapListClientToListClientDTO(List<Client> clientList);

    Client mapClientToClientDTO(ClientRequestDTO clientDTO);



    @Mapping(target = "numbers", source = "rooms")
    @Mapping(target = "bookingId", source = "bookings")
    ClientAndRoomsInfoResponseDTO mapClientToClientAndRoomsInfoDTO(Client client);


    default List<Integer> mapRoomsToNumbers(List<Room> rooms) {
        return rooms.stream()
                .map(Room::getNumber)
                .collect(Collectors.toList());
    }

    default List<Long> mapBookingsToBookingId(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> booking.getId())
                .collect(Collectors.toList());
    }
}
