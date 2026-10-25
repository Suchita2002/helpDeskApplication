package com.example.helpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helpdesk.dto.request.TicketRequest;
import com.example.helpdesk.dto.response.TicketResponse;
import com.example.helpdesk.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService)
	{
		this.ticketService = ticketService;
	}
	
	@PostMapping
	public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody TicketRequest request)
	{
		TicketResponse response=ticketService.createTicket(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<TicketResponse>> getAllTickets()
	{
		return ResponseEntity.ok(ticketService.getAllTickets());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id)
	{
		return ResponseEntity.ok(ticketService.getTicketById(id));
	}

	// Update a ticket
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketRequest request) {

        TicketResponse response = ticketService.updateTicket(id, request);

        return ResponseEntity.ok(response);
    }

    // Delete a ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id) {

        ticketService.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }

}
