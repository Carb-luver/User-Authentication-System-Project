package com.RemoteMode.internshipjava2.model;

import com.RemoteMode.internshipjava2.service.MarketDataService;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class RateInfo {

    String symbolName;

    @Getter
    String symbolUnit;
    Long startPrice;

    @Setter
    Long lastPrice;
    Rate rate;
    MarketDataService marketDataService;
    Map<String, Rate> rateMap = marketDataService.getRateMap();


    public RateInfo(String symbolName, String symbolUnit, Long lastPrice) {
        this.symbolName = symbolName;
        this.symbolUnit = symbolUnit;
        this.lastPrice = lastPrice;
        rate = rateMap.get(symbolUnit);
        this.startPrice = rate.getValue();
    }

}
