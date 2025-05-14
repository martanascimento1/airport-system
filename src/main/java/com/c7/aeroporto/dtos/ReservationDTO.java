package com.c7.aeroporto.dtos;

public record ReservationDTO(
        Long flightId,
        Long passengerId,
        String seatNumber
) {}


