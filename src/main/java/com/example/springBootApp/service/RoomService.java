package com.example.springBootApp.service;

import com.example.springBootApp.controller.dto.response.RoomInfoResponseDTO;
import com.example.springBootApp.entity.Room;
import com.example.springBootApp.exception.RoomNotFoundException;
import com.example.springBootApp.mapper.RoomMapper;
import com.example.springBootApp.repository.ClientRepository;
import com.example.springBootApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    public Room findRoomByNumber(int number) throws RoomNotFoundException {
        Optional<Room> room = roomRepository.findByNumber(number);
        if(room.isEmpty()) throw new RoomNotFoundException(number);
        return room.get();
    }

    public RoomInfoResponseDTO findByNumber(int number) throws RoomNotFoundException {
        Room room = findRoomByNumber(number);
        return roomMapper.mapRoomToRoomInfoResponseDTO(room);
    }
}
