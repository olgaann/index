package com.example.springBootApp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
public class ClientCreationRequestDTO {
    @NotEmpty(message = "Name should not be blank")
    private String name;
    @NotEmpty(message = "Phone should not be blank")
    private String phone;
}
