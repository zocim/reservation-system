package com.charmwithjava.reservationsystem.service;

import com.charmwithjava.reservationsystem.entity.BusDetails;
import com.charmwithjava.reservationsystem.entity.Ticket;
import com.charmwithjava.reservationsystem.repository.BusRepository;
import com.charmwithjava.reservationsystem.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookingService {

    private TicketRepository ticketRepository;

    private BusRepository busRepository;

    public BookingService(TicketRepository ticketRepository, BusRepository busRepository) {
        this.ticketRepository = ticketRepository;
        this.busRepository = busRepository;
    }

    private void saveTicket(String firstName, String lastName, String gender, BusDetails busDetails) throws SeatNotAvailable {
        if(busDetails.getCapacity() <= busDetails.getTickets().size()) {
            throw new SeatNotAvailable();
        }

        Ticket ticket = new Ticket();

        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setGender(gender);

        busDetails.addTicket(ticket);
        ticketRepository.save(ticket);
    }

    @Transactional
    public void bookTicket() throws SeatNotAvailable, InterruptedException {
        Optional<BusDetails> busDetails = busRepository.findById(1L);

        if(busDetails.isPresent()) {
            saveTicket("John", "Allen", "M", busDetails.get());
        }

        Thread.sleep(1000);
    }

    @Transactional
    public void bookTicket1() throws SeatNotAvailable, InterruptedException {
        Optional<BusDetails> busDetails = busRepository.findById(1L);

        if(busDetails.isPresent()) {
            saveTicket("Maria", "Allen", "F", busDetails.get());
        }

        Thread.sleep(1000);
    }
}
