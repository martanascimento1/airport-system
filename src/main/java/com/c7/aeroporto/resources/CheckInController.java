package com.c7.aeroporto.resources;

import com.c7.aeroporto.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping("/{reservationId}")
    public ResponseEntity<?> checkIn(
            @PathVariable Long reservationId,
            @RequestParam String seatNumber) {
        return ResponseEntity.ok(checkInService.checkIn(reservationId, seatNumber));
    }
}