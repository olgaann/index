package com.example.springBootApp.controller;

import com.example.springBootApp.controller.dto.request.ClientRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.exception.ClientNotFoundException;
import com.example.springBootApp.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
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
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientInfoResponseDTO responseDTO = clientService.createClient(clientRequestDTO);
        return ResponseEntity.ok()
                .body("Добавлен пользователь с именем: " + responseDTO.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClientRequestDTO clientRequestDTO) throws ClientNotFoundException {
        clientService.updateClient(id, clientRequestDTO);
        return ResponseEntity.ok()
                .body("Обновлен пользователь с id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) throws ClientNotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.ok()
                .body("Удален пользователь с id: " + id);
    }

}
