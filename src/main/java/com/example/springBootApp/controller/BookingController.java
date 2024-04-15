package com.example.springBootApp.controller;

import com.example.springBootApp.controller.dto.request.ClientRequestDTO;
import com.example.springBootApp.controller.dto.response.BookingInfoResponseDTO;
import com.example.springBootApp.controller.dto.response.ClientInfoResponseDTO;
import com.example.springBootApp.entity.Booking;
import com.example.springBootApp.entity.Client;
import com.example.springBootApp.entity.Room;
import com.example.springBootApp.exception.ClientNotFoundException;
import com.example.springBootApp.exception.RoomNotFoundException;
import com.example.springBootApp.repository.BookingRepository;
import com.example.springBootApp.repository.ClientRepository;
import com.example.springBootApp.repository.RoomRepository;
import com.example.springBootApp.service.BookingService;
import com.example.springBootApp.service.ClientService;
import com.example.springBootApp.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final RoomService roomService;
    private final ClientRepository clientRepository;
    private final RoomRepository roomRepository;

    @GetMapping("/")
    public ResponseEntity<List<BookingInfoResponseDTO>> getAllBookings() {
        List<BookingInfoResponseDTO> bookingInfoResponseDTOList = bookingService.getAllBookings();
        return ResponseEntity.ok()
                .body(bookingInfoResponseDTOList);
    }

//    @GetMapping("/test/{clientId}")
//    public ResponseEntity<List<Booking>> test(@PathVariable("clientId") Long clientId) throws ClientNotFoundException {
//            return ResponseEntity.ok(bookingService.findAllByClientId(clientId));
//
//    }

//    @GetMapping("/test/{clientId}")
//    public ResponseEntity<List<BookingInfoResponseDTO>> test(@PathVariable("clientId") Long clientId) throws ClientNotFoundException {
//        return ResponseEntity.ok(bookingService.findAllByClientId(clientId));
//
//    }




    @PostMapping("/")
    public ResponseEntity<BookingInfoResponseDTO> addBooking(@RequestParam(value = "clientId") Long id,
                                                             @RequestParam(value = "number") int number,
                                                             @RequestParam(value = "nights") int nights) throws ClientNotFoundException, RoomNotFoundException {

        BookingInfoResponseDTO bookingInfoResponseDTO = bookingService.addBooking(id, number, nights);
        return ResponseEntity.ok()
                .body(bookingInfoResponseDTO);
    }

    @DeleteMapping("/{clientId}") //удаляет все бронирования клиента
    public ResponseEntity<List<BookingInfoResponseDTO> > deleteClient(@PathVariable("clientId") Long clientId) throws ClientNotFoundException {
        List<BookingInfoResponseDTO> deleted = bookingService.deleteBooking(clientId);
        return ResponseEntity.ok()
                .header("message", "Bookings have been deleted")
                .body(deleted);
    }

}
