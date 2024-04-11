package com.example.springBootApp.controller;

import com.example.springBootApp.controller.dto.request.ClientCreationRequestDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createClient(@RequestBody ClientCreationRequestDTO clientCreationRequestDTO) {
        clientService.createClient(clientCreationRequestDTO);
    }
}
