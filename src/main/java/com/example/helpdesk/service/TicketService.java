package com.example.helpdesk.service;

import java.util.List;

import com.example.helpdesk.dto.request.TicketRequest;
import com.example.helpdesk.dto.response.TicketResponse;

public interface TicketService {
	
	TicketResponse createTicket(TicketRequest request);
	
	List<TicketResponse> getAllTickets();

    TicketResponse getTicketById(Long id);

    TicketResponse updateTicket(Long id, TicketRequest request);

    void deleteTicket(Long id);
}
