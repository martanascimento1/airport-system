package com.c7.aeroporto.dtos;

public class ReservationRequestDTO {
    private String passengerName;
    private boolean addInsurance;
    private boolean addMeal;
    private boolean addSeatSelection;

    // Getters e Setters
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String name) { this.passengerName = name; }

    public boolean isAddInsurance() { return addInsurance; }
    public void setAddInsurance(boolean addInsurance) { this.addInsurance = addInsurance; }

    public boolean isAddMeal() { return addMeal; }
    public void setAddMeal(boolean addMeal) { this.addMeal = addMeal; }

    public boolean isAddSeatSelection() { return addSeatSelection; }
    public void setAddSeatSelection(boolean addSeatSelection) { this.addSeatSelection = addSeatSelection; }
}
