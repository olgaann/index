package com.example.springBootApp.mapper;

import com.example.springBootApp.controller.dto.response.ClientAndRoomsInfoResponseDTO;
import com.example.springBootApp.controller.dto.response.RoomInfoResponseDTO;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomInfoResponseDTO mapRoomToRoomInfoResponseDTO(Room room);
}
