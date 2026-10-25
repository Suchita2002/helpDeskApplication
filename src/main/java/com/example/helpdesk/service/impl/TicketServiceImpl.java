package com.example.helpdesk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.helpdesk.dto.request.TicketRequest;
import com.example.helpdesk.dto.response.TicketResponse;
import com.example.helpdesk.entity.Ticket;
import com.example.helpdesk.exception.TicketNotFoundException;
import com.example.helpdesk.mapper.TicketMapper;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	private final TicketRepository ticketRepository;
	
	public TicketServiceImpl(TicketRepository ticketRepository)
	{
		this.ticketRepository = ticketRepository;
	}

	@Override
	@Transactional
	public TicketResponse createTicket(TicketRequest request) {
		Ticket ticket = TicketMapper.toEntity(request);
		Ticket savedTicket = ticketRepository.save(ticket);
		return TicketMapper.toResponse(savedTicket);
	}

	@Override
	@Transactional( readOnly = true )
	public List<TicketResponse> getAllTickets() {
		List<Ticket> tickets = ticketRepository.findAll();
		List<TicketResponse> response = tickets
				.stream()
				.map(TicketMapper::toResponse)
				.toList();
		return response;
	}

	@Override
	@Transactional( readOnly = true )
	public TicketResponse getTicketById(Long id) {
		Ticket ticket = findTicketOrThrow(id);
		return TicketMapper.toResponse(ticket);
	}

	@Override
    @Transactional
    public TicketResponse updateTicket(Long id, TicketRequest request) {
        Ticket ticket = findTicketOrThrow(id);

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setCategory(request.getCategory());
        ticket.setPriority(request.getPriority());

        Ticket updatedTicket = ticketRepository.save(ticket);

        return TicketMapper.toResponse(updatedTicket);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        Ticket ticket = findTicketOrThrow(id);

        ticketRepository.delete(ticket);
    }
	
	private Ticket findTicketOrThrow(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

}
