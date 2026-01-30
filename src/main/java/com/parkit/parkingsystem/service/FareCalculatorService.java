package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.util.Date;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        double taxedDurationInHour = howManyTime(ticket);
//
//        Date dateIn = ticket.getInTime();
//        Date dateOut = ticket.getOutTime();
//        int durationInMinutes = (int) (dateOut.getTime() - dateIn.getTime()) / (1000 * 60); //1000ms * 60 s = in minute
//        double taxedDurationInHour = durationInMinutes < Fare.MAX_TIME_FOR_FREE_IN_MINUTES ? 0 : (double) durationInMinutes / 60;
        double fare;
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                fare = taxedDurationInHour * Fare.CAR_RATE_PER_HOUR;
                break;
            }
            case BIKE: {
                fare = taxedDurationInHour * Fare.BIKE_RATE_PER_HOUR;
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
//        ticket.setPrice(ticket.getIsRecurrent() ? (fare * (100 - Fare.REDUCE_FOR_RECURRENT_IN_PERCENT) / 100) : fare);
        ticket.setPrice(fareWithReduceIfReccurent(ticket, fare));
    }

    private double howManyTime(Ticket ticket){
        Date dateIn = ticket.getInTime();
        Date dateOut = ticket.getOutTime();
        int durationInMinutes = (int) (dateOut.getTime() - dateIn.getTime()) / (1000 * 60); //1000ms * 60 s = in minute
        return (durationInMinutes < Fare.MAX_TIME_FOR_FREE_IN_MINUTES ? 0 : (double) durationInMinutes / 60);
    }

    private double fareWithReduceIfReccurent(Ticket ticket, double fare){
        return ticket.getIsRecurrent() ? (fare * (100 - Fare.REDUCE_FOR_RECURRENT_IN_PERCENT) / 100) : fare;
    }
}