package com.example.springBootApp.service;

import com.example.springBootApp.controller.dto.response.BookingInfoResponseDTO;
import com.example.springBootApp.entity.Booking;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.entity.Room;
import com.example.springBootApp.exception.ClientNotFoundException;
import com.example.springBootApp.exception.RoomNotFoundException;
import com.example.springBootApp.mapper.BookingMapper;
import com.example.springBootApp.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final RoomService roomService;
    private final BookingMapper bookingMapper;

    public BookingInfoResponseDTO addBooking(Long clientId, int number, int nights) throws ClientNotFoundException, RoomNotFoundException {
        Client client = clientService.findClientById(clientId);
        Room room = roomService.findRoomByNumber(number);
        Booking booking = new Booking();
        booking.setClient(client);
        booking.setRoom(room);
        booking.setNights(nights);
        double totalPrice = nights * room.getPrice();
        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);
        return bookingMapper.mapBookingToBookingInfoResponseDTO(booking);
    }

    public List<BookingInfoResponseDTO> getAllBookings() {
        return bookingMapper.mapListBookingToListBookingInfoResponseDTO(bookingRepository.findAll());
    }

    public List<BookingInfoResponseDTO> deleteBooking(Long clientId) {
        List<Booking> bookingsToDelete = findAllByClientId(clientId);
        bookingRepository.deleteAll(bookingsToDelete);
        return bookingMapper.mapListBookingToListBookingInfoResponseDTO(bookingsToDelete);
    }

    public List<Booking> findAllByClientId(Long clientId) {
        return bookingRepository.findAllByClientId(clientId);
    }
}
