package com.example.springBootApp.controller;

import com.example.springBootApp.controller.dto.response.ClientAndRoomsInfoResponseDTO;
import com.example.springBootApp.controller.dto.response.RoomInfoResponseDTO;
import com.example.springBootApp.entity.Room;
import com.example.springBootApp.exception.ClientNotFoundException;
import com.example.springBootApp.exception.RoomNotFoundException;
import com.example.springBootApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;


    @GetMapping("/{number}")
    public RoomInfoResponseDTO getRoomByNumber(@PathVariable("number") int nubmer) throws RoomNotFoundException {
        RoomInfoResponseDTO RoomInfoResponseDTO = roomService.findByNumber(nubmer);
        return RoomInfoResponseDTO;
    }
}
