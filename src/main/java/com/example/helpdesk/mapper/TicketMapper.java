package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.TicketRequest;
import com.example.helpdesk.dto.response.TicketResponse;
import com.example.helpdesk.entity.Ticket;

public class TicketMapper {
	
	public TicketMapper() {
		
	}
	
	public static Ticket toEntity(TicketRequest request)
	{
		Ticket ticket = new Ticket();
		
		ticket.setTitle(request.getTitle());
		ticket.setDescription(request.getDescription());
		ticket.setCategory(request.getCategory());
		ticket.setPriority(request.getPriority());
		return ticket;
	}
	
	public static TicketResponse toResponse(Ticket ticket)
	{
		TicketResponse response = new TicketResponse();
		response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setCategory(ticket.getCategory());
        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());
        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());
        
        return response;
	}

}
