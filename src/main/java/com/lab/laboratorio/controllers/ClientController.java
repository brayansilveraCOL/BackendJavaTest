package com.lab.laboratorio.controllers;

import com.lab.laboratorio.entities.Client;
import com.lab.laboratorio.repository.ClientRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;


@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Operations Clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @Operation(summary = "Get all Clients")
    @ApiResponse(
            responseCode = "200",
            description = "Get All Clients",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Client.class)
            )
    )
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Get Client By Id",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Client.class)
            )
    )
    public ResponseEntity<Client> getClientById(
            @Parameter(description = "Id Database")
            @PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @ApiResponse(
            responseCode = "200",
            description = "Get Clients By Identify",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Client.class)
            )
    )
    public ResponseEntity<?> searchByIdentify(
            @Parameter(description = "String Identify")
            @RequestParam String identify) {
        List<Client> clients = clientRepository.findAllByIdentify(identify);
        return clients.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(clients);
    }

}
