package com.c7.aeroporto.entities;
import com.c7.aeroporto.entities.enums.ReservationStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Flight flight;
        @ManyToOne
        @JoinColumn(name = "passenger_id", nullable = false)
        private Passenger passenger;

        private String seatNumber;
        private LocalDateTime bookingDate;

        @Enumerated(EnumType.STRING)
        private ReservationStatus status;

    public void setSeatNumber(String s) {
    }

    public void setFlight(Flight newFlight) {
    }

    public void setPassenger(Passenger passenger) {
    }

    public void setStatus(ReservationStatus reservationStatus) {
    }

    public void setBookingDate(LocalDateTime now) {
    }

    public void setCheckedIn(boolean b) {
    }
}
