package com.example.springBootApp.controller.dto.response;

import com.example.springBootApp.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ClientAndRoomsInfoResponseDTO {
    private String name;
    private String phone;
    private List<Integer> numbers;
    private List<Long> bookingId;

}
