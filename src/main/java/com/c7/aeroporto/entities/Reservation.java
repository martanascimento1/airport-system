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

        private boolean hasInsurance;
        private boolean hasSpecialMeal;
        private boolean hasSeatSelection;
        private Double totalCost;
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

    public void setPassengerName(String passengerName) {
    }

    public void setHasInsurance(boolean hasInsurance) {
    }

    public void setHasSpecialMeal(boolean hasMeal) {
    }

    public void setHasSeatSelection(boolean hasSeatSelection) {
    }

    public void setTotalCost(double cost) {
        this.totalCost = totalCost;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
