package com.example.springBootApp.repository;

import com.example.springBootApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    void deleteByClientId(Long clientId);

    List<Booking> findAllByClientId(Long clientId);
}
