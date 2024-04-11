package com.example.springBootApp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients", schema = "boot")
public class Client {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
}
