package com.jetbrains.gemmy.punishment.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Btc {
    private long timestampInSeconds;
    private double lowPrice;
    private double highPrice;
    private double openPrice;
    private double closePrice;
    private double volume;

    public long getTimestampInSeconds() {
        return timestampInSeconds;
    }

    public void setTimestampInSeconds(long timestampInSeconds) {
        this.timestampInSeconds = timestampInSeconds;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public static Btc mapToBtc(ArrayList<Double> splittedData) {
        Btc btc = new Btc();

        btc.timestampInSeconds = splittedData.get(0).longValue();
        btc.lowPrice = splittedData.get(1);
        btc.highPrice = splittedData.get(2);
        btc.openPrice = splittedData.get(3);
        btc.closePrice = splittedData.get(4);
        btc.volume = splittedData.get(5);

        return btc;
    }

    public ArrayList<Double> toInfoRow() {
        ArrayList<Double> btcInfoRow = new ArrayList<>();

        btcInfoRow.add((double)timestampInSeconds);
        btcInfoRow.add(lowPrice);
        btcInfoRow.add(highPrice);
        btcInfoRow.add(openPrice);
        btcInfoRow.add(closePrice);
        btcInfoRow.add(volume);

        return btcInfoRow;
    }
}
