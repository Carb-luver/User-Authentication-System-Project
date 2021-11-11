package com.RemoteMode.internship2correction.model;

import com.RemoteMode.internship2correction.service.MarketDataService;
import lombok.Getter;
import lombok.Setter;

public class RateInfo {

    String symbolName;
    @Getter String symbolUnit;
    Long startPrice;
    @Setter Long lastPrice;
    Rate rate;

    StartPrice startPriceClass;
    MarketDataService marketDataService;


    public RateInfo(String symbolName, String symbolUnit, Long lastPrice, Rate rate) {
        this.symbolName = symbolName;
        this.symbolUnit = symbolUnit;
        this.lastPrice = lastPrice;
        this.rate = rate;
        this.startPrice = startPriceClass.getStartPriceMap().get(symbolName);
    }

}
