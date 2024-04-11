package com.example.springBootApp.controllers;
import com.example.springBootApp.entities.DTO.ClientDTO;
import com.example.springBootApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> getClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

}
