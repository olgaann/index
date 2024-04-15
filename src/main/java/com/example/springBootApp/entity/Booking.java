package com.example.springBootApp.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings", schema = "boot")
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "nights")
    private int nights;

    @Column(name = "total_price")
    private double totalPrice;

    @Override
    public String toString() {
        return "Booking{" +
                ", client=" + client.getName() +
                ", room=" + room.getNumber() +
                ", nights=" + nights +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

