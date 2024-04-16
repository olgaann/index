package com.example.springBootApp.service;

import com.example.springBootApp.controller.dto.response.RoomInfoResponseDTO;
import com.example.springBootApp.entity.Room;
import com.example.springBootApp.exception.RoomNotFoundException;
import com.example.springBootApp.mapper.RoomMapper;
import com.example.springBootApp.repository.RoomRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomMapper roomMapper;

    static int[][] genTestData() {
        return new int[][] {{101, 5000}, {102, 6000}, {103, 10000}};
    }

    @ParameterizedTest
    @MethodSource("genTestData")
    public void testFindRoomByNumber_WhenRoomExists_ReturnRoom(int[] data) throws RoomNotFoundException {
        Room room = new Room();
        int roomNumber = data[0];
        double price = data[1];
        room.setNumber(roomNumber);
        room.setPrice(price);
        Mockito.when(roomRepository.findByNumber(roomNumber)).thenReturn(Optional.of(room));

        Room actualRoom = roomService.findRoomByNumber(roomNumber);
        assertNotNull(actualRoom);
        assertEquals(room, actualRoom);
    }

    @ParameterizedTest
    @ValueSource(ints = {301, 302, 303})
    public void testFindRoomByNumber_WhenRoomNotExists_ThrowException(int roomNumber) {
        Mockito.when(roomRepository.findByNumber(roomNumber)).thenReturn(Optional.empty());
        RoomNotFoundException exception = assertThrows(RoomNotFoundException.class, () -> roomService.findRoomByNumber(roomNumber));
        assertEquals(String.format("В отеле нет номера %d", roomNumber), exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("genTestData")
    public void testFindByNumber_WhenRoomExists_ReturnRoomInfoResponseDTO(int[] data) throws RoomNotFoundException {
        Room room = new Room();
        int roomNumber = data[0];
        double price = data[1];
        room.setNumber(roomNumber);
        room.setPrice(price);

        Mockito.when(roomRepository.findByNumber(roomNumber)).thenReturn(Optional.of(room));

        RoomInfoResponseDTO expectedRoomInfoResponseDTO = new RoomInfoResponseDTO();
        expectedRoomInfoResponseDTO.setNumber(roomNumber);
        expectedRoomInfoResponseDTO.setPrice(price);

        Mockito.when(roomMapper.mapRoomToRoomInfoResponseDTO(room)).thenReturn(expectedRoomInfoResponseDTO);

        RoomInfoResponseDTO actualRoomInfoResponseDTO = roomService.findByNumber(roomNumber);
        assertNotNull(actualRoomInfoResponseDTO);
        assertEquals(expectedRoomInfoResponseDTO, actualRoomInfoResponseDTO);
    }

    @ParameterizedTest
    @ValueSource(ints = {301, 302, 303})
    public void testFindByNumber_WhenRoomNotExists_ThrowException(int roomNumber) {
        Mockito.when(roomRepository.findByNumber(roomNumber)).thenReturn(Optional.empty());
        RoomNotFoundException exception = assertThrows(RoomNotFoundException.class, () -> roomService.findByNumber(roomNumber));
        assertEquals(String.format("В отеле нет номера %d", roomNumber), exception.getMessage());
    }
}