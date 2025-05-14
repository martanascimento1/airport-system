package com.c7.aeroporto.repositories;
import com.c7.aeroporto.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPassengerId(Long passengerId);

    List<Reservation> findByFlightId(Long flightId);
}