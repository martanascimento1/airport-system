package com.c7.aeroporto.entities.decorators;
import com.c7.aeroporto.repositories.ReservationComponent;

// COMPONENTE CONCRETO

// implementacao padrão de uma reserva, pode ser usada sozinha ou envolvida com algum decorador para adicionar mais funcionalidades:
public class BasicReservation implements ReservationComponent {

    private final String passengerName;

    public BasicReservation(String passengerName) {
        this.passengerName = passengerName;
    }

    @Override
    public String getDetails() {
        return "Reserva básica para " + passengerName;
    }

    @Override
    public double getCost() {
        return 500.0; // valor base
    }
}
