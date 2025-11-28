package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.util.Date;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        //TODO: Some tests are failing here. Need to check if this logic is correct
        Date dateIn = ticket.getInTime();
        Date dateOut = ticket.getOutTime();
        int durationInMinutes = (int) (dateOut.getTime() - dateIn.getTime()) / (1000 * 60); //1000ms * 60 s = in minute
        float taxedDurationInHour = durationInMinutes < Fare.MAX_TIME_FOR_FREE_IN_MINUTES ? 0 : (float) durationInMinutes / 60;

        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(taxedDurationInHour * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(taxedDurationInHour * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}