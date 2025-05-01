package com.c7.aeroporto.services;

import com.c7.aeroporto.entities.Reservation;
import com.c7.aeroporto.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    private final ReservationRepository reservationRepository;

    public CheckInService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation checkIn(Long reservationId, String seatNumber) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservation.setSeatNumber(seatNumber);
        reservation.setCheckedIn(true);

        return reservationRepository.save(reservation);
    }
}