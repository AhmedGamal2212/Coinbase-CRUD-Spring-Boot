package com.jetbrains.gemmy.punishment.service;

import com.jetbrains.gemmy.punishment.model.Btc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BtcServiceTest {
    BtcService btcService;

    @BeforeEach
    void setUpBtcService() {
        btcService = new BtcService(new RestTemplate());
    }

    @Test
    void getAllDataShouldReturnThreeHundredRows() {
        ArrayList<ArrayList<Double>> allBtcData = btcService.getAll();

        assertEquals(300, allBtcData.size());
    }

    @Test
    void getAllDataRowsShouldHaveSixFieldsEach() {
        ArrayList<ArrayList<Double>> allBtcData = btcService.getAll();

        for (ArrayList<Double> row : allBtcData) {
            assertEquals(6, row.size());
        }
    }

    @Test
    void getByIdShouldReturnSingleRow() {
        ArrayList<Double> btcInfoRow = btcService.getById(1);

        assertNotNull(btcInfoRow);
        assertEquals(6, btcInfoRow.size());
    }

    @Test
    void deleteByIdShouldDeleteTheRowWithProperIndex() {
        ArrayList<Double> secondBtcInfoRowBeforeDeletion = btcService.getById(2);
        btcService.deleteById(1);
        ArrayList<Double> firstBtcInfoFowAfterDeletion = btcService.getById(1);
        ArrayList<ArrayList<Double>> allBtcData = btcService.getAll();

        assertEquals(firstBtcInfoFowAfterDeletion, secondBtcInfoRowBeforeDeletion);
        assertEquals(299, allBtcData.size());
    }

    @Test
    void addShouldInsertNewBtcInfoRow() {
        double timestampInSeconds = 1d, lowPrice = 2d, highPrice = 3d, openPrice = 4d, closePrice = 5d, volume = 6d;
        ArrayList<Double> btcInfoRow = new ArrayList<>(
                Arrays.asList(timestampInSeconds, lowPrice, highPrice, openPrice, closePrice, volume)
        );
        Btc btc = Btc.mapToBtc(btcInfoRow);

        btcService.add(btc);
        ArrayList<ArrayList<Double>> allBtcData = btcService.getAll();
        ArrayList<Double> lastBtcInfoRow = btcService.getById(301);

        assertEquals(301, allBtcData.size());
        assertEquals(btc.toInfoRow(), lastBtcInfoRow);
    }

    @Test
    void modifyShouldChangeBtcInfoRowUsingId() {
        double timestampInSeconds = 1d, lowPrice = 2d, highPrice = 3d, openPrice = 4d, closePrice = 5d, volume = 6d;
        ArrayList<Double> btcInfoRow = new ArrayList<>(
                Arrays.asList(timestampInSeconds, lowPrice, highPrice, openPrice, closePrice, volume)
        );

        btcService.add(Btc.mapToBtc(btcInfoRow));

        ArrayList<Double> modifiedBtcInfoRow = new ArrayList<>(
                Arrays.asList(timestampInSeconds + 1, lowPrice + 1, highPrice + 1, openPrice + 1, closePrice + 1, volume + 1)
        );

        btcService.modify(301, Btc.mapToBtc(modifiedBtcInfoRow));
        ArrayList<Double> lastBtcInfoRow = btcService.getById(301);

        assertEquals(modifiedBtcInfoRow, lastBtcInfoRow);
    }
}