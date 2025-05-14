package com.c7.aeroporto.observer;
import java.util.ArrayList;
import java.util.List;

//subject
public class FlightNotifier {

    // armazena a lista de observadores (passageiros) e notifica todos eles quando algo acontece com o voo.
    private final List<Observer> observers = new ArrayList<>();

    // adiciona um observer a lista
    public void addObserver(Observer o) {
        observers.add(o);
    }
    //Quando um evento ocorre, percorre a lista e chama update() em cada um dos observadores
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}

