package com.c7.aeroporto.observer;
import com.c7.aeroporto.entities.Reservation;

// Observer concreto
//implementa Observer e representa um passageiro
public class PassengerObserver implements Observer {

    private final Reservation reservation;

    // envolve um reservation para acessar o nome do passageiro
    public PassengerObserver(Reservation reservation) {
        this.reservation = reservation;
    }

    // implementacao simples, a notificação é apenas mostrada no console quando update() é chamado
    @Override
    public void update(String message) {
        System.out.println("Passageiro " + reservation.getPassenger().getName()
                + " recebeu notificação: " + message);
    }
}

