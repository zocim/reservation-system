package com.charmwithjava.reservationsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket_details")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busDetails;
}
