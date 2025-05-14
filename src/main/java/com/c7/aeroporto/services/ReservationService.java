package com.c7.aeroporto.services;
import com.c7.aeroporto.dtos.ReservationDTO;
import com.c7.aeroporto.dtos.ReservationModificationDTO;
import com.c7.aeroporto.entities.Flight;
import com.c7.aeroporto.entities.Passenger;
import com.c7.aeroporto.entities.Reservation;
import com.c7.aeroporto.entities.enums.ReservationStatus;
import com.c7.aeroporto.repositories.FlightRepository;
import com.c7.aeroporto.repositories.PassengerRepository;
import com.c7.aeroporto.repositories.ReservationRepository;
import com.c7.aeroporto.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              FlightRepository flightRepository,
                              PassengerRepository passengerRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public Reservation createReservation(ReservationDTO dto) {
        Flight flight = flightRepository.findById(dto.flightId())
                .orElseThrow(() -> new RuntimeException("Voo não encontrado"));

        Passenger passenger = passengerRepository.findById(dto.passengerId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setSeatNumber(dto.seatNumber());
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setBookingDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public Reservation modifyReservation(Long id, ReservationModificationDTO dto) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        if (dto.seatNumber() != null) {
            reservation.setSeatNumber(dto.seatNumber());
        }
        if (dto.newFlightId() != null) {
            Flight newFlight = flightRepository.findById(dto.newFlightId())
                    .orElseThrow(() -> new ResourceNotFoundException("New flight not found"));
            reservation.setFlight(newFlight);
        }

        reservation.setStatus(ReservationStatus.MODIFIED);
        return reservationRepository.save(reservation);
    }
}