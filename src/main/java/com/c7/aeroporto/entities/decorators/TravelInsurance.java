package com.c7.aeroporto.entities.decorators;
import com.c7.aeroporto.repositories.ReservationComponent;


// DECORADOR CONCRETO

public class TravelInsurance extends ReservationDecorator {

    public TravelInsurance(ReservationComponent reservation) {
        super(reservation);
    }

    // adiciona seguro de viagem aos detalhes da reserva
    @Override
    public String getDetails() {
        return super.getDetails() + " + Seguro de viagem";
    }

    // incrementa o custo
    @Override
    public double getCost() {
        return super.getCost() + 120.0;
    }
}