package com.example.springBootApp.controller.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingInfoResponseDTO {
    private String name;
    private String phone;
    private int number;
    private int price;
    private int nights;
    private double totalPrice;
}
