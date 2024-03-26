package com.charmwithjava.reservationsystem.repository;

import com.charmwithjava.reservationsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
