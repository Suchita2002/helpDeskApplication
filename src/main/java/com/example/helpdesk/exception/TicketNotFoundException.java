package com.example.helpdesk.exception;

public class TicketNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException(Long id)
	{
		super("Ticket not found with id: " + id);
	}

}
