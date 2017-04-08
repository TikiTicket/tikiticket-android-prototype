package com.veinhorn.tikiticket.android.core;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by veinhorn on 1.4.17.
 * Обёртка над объектом билета, которая используется в UI
 */

public class TicketWrapper {
    private Long orderNumber;

    private String fromStation;
    private String toStation;
    private BigDecimal cost;
    private Date dispatchDate;
    private Integer numberOfSeats;
    private String trainNumber;

    /** Показывает, действителен ли данный билет для поездки */
    private boolean isActive;

    /** Форматирует дату отправления в удобочитаемый вид */
    public String getFormattedDispatchDate() {
        return "сегодня 12:30";
    }

    public String createRoute() {
        return fromStation + " - " + toStation;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = new BigDecimal(cost).setScale(2, BigDecimal.ROUND_DOWN);
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
