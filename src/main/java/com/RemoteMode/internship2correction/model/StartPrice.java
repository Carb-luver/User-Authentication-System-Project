package com.RemoteMode.internship2correction.model;

import java.util.Map;

public class StartPrice {

    String name;
    long startPrice;
    private Map<String, Long> startPriceMap;

    public StartPrice(String name, long startPrice) {
        this.name = name;
        this.startPrice = startPrice;
        startPriceMap.put(name,startPrice);
    }

    public Map<String, Long> getStartPriceMap() {
        return startPriceMap;
    }

}
