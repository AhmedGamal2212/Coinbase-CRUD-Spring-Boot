package com.jetbrains.gemmy.punishment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Setter
@Getter
public class Btc {
    private long timestampInSeconds;
    private double lowPrice;
    private double highPrice;
    private double openPrice;
    private double closePrice;
    private double volume;

    public static Btc mapToBtc(ArrayList<Double> splittedData) {
        Btc btc = new Btc();

        btc.setTimestampInSeconds(splittedData.get(0).longValue());
        btc.setLowPrice(splittedData.get(1));
        btc.setHighPrice(splittedData.get(2));
        btc.setOpenPrice(splittedData.get(3));
        btc.setClosePrice(splittedData.get(4));
        btc.setVolume(splittedData.get(5));

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
