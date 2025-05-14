package com.c7.aeroporto.entities.decorators;
import com.c7.aeroporto.repositories.ReservationComponent;

// DECORADOR CONCRETO

// Estende reservationDecorator e modifica ou adiciona comportamento a getDetails() e getCost()
public class SpecialMeal extends ReservationDecorator {

    public SpecialMeal(ReservationComponent reservation) {
        super(reservation);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " + Refeição especial";
    }

    @Override
    public double getCost() {
        return super.getCost() + 40.0;
    }
}
