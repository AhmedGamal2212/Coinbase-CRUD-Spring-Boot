package com.jetbrains.gemmy.punishment.service;

import com.jetbrains.gemmy.punishment.model.Btc;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class BtcService {

    private final String url = "https://api.pro.coinbase.com/products/BTC-USD/candles/1m";
    private final ArrayList<ArrayList<Double>> btcInfo;

    public BtcService(@NotNull RestTemplate restTemplate) {
        btcInfo = restTemplate.getForObject(url, ArrayList.class);
    }

    public ArrayList<ArrayList<Double>> getAll() {
        return btcInfo;
    }

    public ArrayList<Double> getById(int id) {
        int index = id - 1;
        return btcInfo.get(index);
    }

    public void deleteById(int id) {
        int index = id - 1;
        btcInfo.remove(index);
    }

    public void add(Btc btc) {
        btcInfo.add(btc.toInfoRow());
    }

    public void modify(int id, Btc btc) {
        int index = id - 1;
        btcInfo.set(index, btc.toInfoRow());
    }
}
