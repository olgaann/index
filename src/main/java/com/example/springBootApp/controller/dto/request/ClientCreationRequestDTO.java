package com.example.springBootApp.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientCreationRequestDTO {
    private String name;
    private String phone;
}
