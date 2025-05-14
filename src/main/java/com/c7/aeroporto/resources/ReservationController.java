package com.c7.aeroporto.resources;

import com.c7.aeroporto.dtos.ReservationDTO;
import com.c7.aeroporto.dtos.ReservationModificationDTO;
import com.c7.aeroporto.dtos.ReservationRequestDTO;
import com.c7.aeroporto.entities.*;
import com.c7.aeroporto.entities.decorators.BasicReservation;
import com.c7.aeroporto.entities.decorators.SeatSelection;
import com.c7.aeroporto.entities.decorators.SpecialMeal;
import com.c7.aeroporto.entities.decorators.TravelInsurance;
import com.c7.aeroporto.repositories.ReservationComponent;
import com.c7.aeroporto.repositories.ReservationRepository;
import com.c7.aeroporto.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;


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

    @PostMapping("/custom")
    public ResponseEntity<String> createCustomReservation(@RequestBody ReservationRequestDTO dto) {

        ReservationComponent reservation = new BasicReservation(dto.getPassengerName());

        // Flags para registrar no banco
        boolean hasInsurance = false;
        boolean hasMeal = false;
        boolean hasSeatSelection = false;

        // Aplica os decoradores
        if (dto.isAddInsurance()) {
            reservation = new TravelInsurance(reservation);
            hasInsurance = true;
        }
        if (dto.isAddMeal()) {
            reservation = new SpecialMeal(reservation);
            hasMeal = true;
        }
        if (dto.isAddSeatSelection()) {
            reservation = new SeatSelection(reservation);
            hasSeatSelection = true;
        }

        // Transforma em entidade real e persiste
        Reservation entity = new Reservation();
        entity.setPassengerName(dto.getPassengerName());
        entity.setHasInsurance(hasInsurance);
        entity.setHasSpecialMeal(hasMeal);
        entity.setHasSeatSelection(hasSeatSelection);
        entity.setTotalCost(reservation.getCost());

        reservationRepository.save(entity);

        return ResponseEntity.ok("Reserva criada com sucesso. Total: R$" + entity.getTotalCost());
    }


}