package com.example.springBootApp.controller;

import com.example.springBootApp.controller.dto.request.ClientCreationRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientInfoResponseDTO> getClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientInfoResponseDTO getClientById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientCreationRequestDTO clientCreationRequestDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = getErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body("Ошибка создания клиента: " + errorMessage);
        }

        ClientInfoResponseDTO responseDTO = clientService.createClient(clientCreationRequestDTO);
        return ResponseEntity.ok()
                .body("Добавлен пользователь с именем: " + responseDTO.getName());
    }

    public String getErrorMessage(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        String errorMessage = errors.stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "));
        return errorMessage;
    }
}
