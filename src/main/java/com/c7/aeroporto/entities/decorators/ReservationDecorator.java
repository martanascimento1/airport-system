package com.c7.aeroporto.entities.decorators;
import com.c7.aeroporto.repositories.ReservationComponent;


// DECORADOR ABSTRAT0
// implementa a mesma interface de Reservation Component
// base para todos os decoradores concretos
public abstract class ReservationDecorator implements ReservationComponent {
    protected ReservationComponent decoratedReservation;

    // referência ao componente que será decorado
    public ReservationDecorator(ReservationComponent reservation) {
        this.decoratedReservation = reservation;
    }

    // chamada ao componente decorado
    @Override
    public String getDetails() {
        return decoratedReservation.getDetails();
    }

    @Override
    public double getCost() {
        return decoratedReservation.getCost();
    }
}

