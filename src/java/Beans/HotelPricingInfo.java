/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author mohammad
 */
public class HotelPricingInfo implements Serializable{

    private double totalPriceValue;

    private double averagePriceValue;

    private double originalPricePerNight;

    private boolean drr;

    private double percentSavings;

    private String currency;

    private double crossOutPriceValue;

    public double getTotalPriceValue() {
        return totalPriceValue;
    }

    public void setTotalPriceValue(double totalPriceValue) {
        this.totalPriceValue = totalPriceValue;
    }

    public double getAveragePriceValue() {
        return averagePriceValue;
    }

    public void setAveragePriceValue(double averagePriceValue) {
        this.averagePriceValue = averagePriceValue;
    }

    public double getOriginalPricePerNight() {
        return originalPricePerNight;
    }

    public void setOriginalPricePerNight(double originalPricePerNight) {
        this.originalPricePerNight = originalPricePerNight;
    }

    public boolean getDrr() {
        return drr;
    }

    public void setDrr(boolean drr) {
        this.drr = drr;
    }

    public double getPercentSavings() {
        return percentSavings;
    }

    public void setPercentSavings(double percentSavings) {
        this.percentSavings = percentSavings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getCrossOutPriceValue() {
        return crossOutPriceValue;
    }

    public void setCrossOutPriceValue(double crossOutPriceValue) {
        this.crossOutPriceValue = crossOutPriceValue;
    }
}
