package com.charmwithjava.reservationsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bus_details")
public class BusDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime departureDateTime;

    private int capacity;

    private String busNumber;

    @Version
    private Long version;

    @OneToMany(mappedBy = "busDetails")
    private Set<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        ticket.setBusDetails(this);
        getTickets().add(ticket);
    }


}
