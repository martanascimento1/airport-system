package com.c7.aeroporto.dtos;

public record ReservationModificationDTO(
        String seatNumber,
        Long newFlightId
) {}
