package com.c7.aeroporto.entities.decorators;
import com.c7.aeroporto.repositories.ReservationComponent;


// DECORADOR CONCRETO

public class SeatSelection extends ReservationDecorator {

    public SeatSelection(ReservationComponent reservation) {
        super(reservation);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " + Escolha de assento";
    }

    @Override
    public double getCost() {
        return super.getCost() + 25.0;
    }
}
