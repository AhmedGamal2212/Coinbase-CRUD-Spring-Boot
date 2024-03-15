package com.jetbrains.gemmy.punishment.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Btc {
    private long timestampInSeconds;
    private double lowPrice;
    private double highPrice;
    private double openPrice;
    private double closePrice;
    private double volume;

    public static Btc mapToBtc(ArrayList<Double> splittedData) {
        return Btc.builder()
                .timestampInSeconds(splittedData.get(0).longValue())
                .lowPrice(splittedData.get(1))
                .highPrice(splittedData.get(2))
                .openPrice(splittedData.get(3))
                .closePrice(splittedData.get(4))
                .volume(splittedData.get(5))
                .build();
    }

    public ArrayList<Double> toInfoRow() {
        return new ArrayList<>() {{
            add((double) timestampInSeconds);
            add(lowPrice);
            add(highPrice);
            add(openPrice);
            add(closePrice);
            add(volume);
        }};
    }
}
