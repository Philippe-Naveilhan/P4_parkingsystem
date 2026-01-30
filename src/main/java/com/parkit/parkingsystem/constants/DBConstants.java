package com.parkit.parkingsystem.constants;

public class DBConstants {

    public static final String GET_NEXT_PARKING_SPOT = "select min(PARKING_NUMBER) from parking where AVAILABLE = true and TYPE = ?";
    public static final String UPDATE_PARKING_SPOT = "update parking set available = ? where PARKING_NUMBER = ?";

    public static final String SAVE_TICKET = "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME, IS_RECURRENT) values(?,?,?,?,?,?)";
    public static final String UPDATE_TICKET = "update ticket set PRICE=?, OUT_TIME=? where ID=?";
    public static final String GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, t.IS_RECURRENT, p.TYPE from ticket t,parking p where t.OUT_TIME IS NULL and t.VEHICLE_REG_NUMBER=?";

    public static final String IS_VEHICLE_IS_OUT = "SELECT COUNT(VEHICLE_REG_NUMBER) FROM ticket WHERE VEHICLE_REG_NUMBER=? AND OUT_TIME IS NULL LIMIT 1";
    public static final String IS_RECURRENT_VEHICLE = "SELECT COUNT(VEHICLE_REG_NUMBER) FROM ticket WHERE VEHICLE_REG_NUMBER=?";
}
