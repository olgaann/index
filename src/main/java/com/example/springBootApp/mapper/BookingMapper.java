package com.example.springBootApp.mapper;

import com.example.springBootApp.controller.dto.response.BookingInfoResponseDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.entity.Booking;
import com.example.springBootApp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "phone", source = "client.phone")
    @Mapping(target = "number", source = "room.number")
    @Mapping(target = "price", source = "room.price")
    BookingInfoResponseDTO mapBookingToBookingInfoResponseDTO(Booking booking);


    List<BookingInfoResponseDTO> mapListBookingToListBookingInfoResponseDTO(List<Booking> bookingList);
}
