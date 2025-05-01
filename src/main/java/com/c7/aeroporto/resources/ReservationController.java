package com.c7.aeroporto.resources;

import com.c7.aeroporto.dtos.ReservationDTO;
import com.c7.aeroporto.dtos.ReservationModificationDTO;
import com.c7.aeroporto.entities.Reservation;
import com.c7.aeroporto.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO dto) {
        return ResponseEntity.ok(reservationService.createReservation(dto));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> modifyReservation(
            @PathVariable Long id,
            @RequestBody ReservationModificationDTO dto) {
        return ResponseEntity.ok(reservationService.modifyReservation(id, dto));
    }
}