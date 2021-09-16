package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.model.Rate;
import com.RemoteMode.internshipjava2.model.RateInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RateCache {
    private Map<String, RateInfo> rateInfoMap;
    RateInfo rateInfo;
    Rate rate;
    MarketDataService marketDataService;
    Iterator<Rate> iterator;

    public void storeRateInfo() {
        List<Rate> listRates = marketDataService.getRates();
        iterator = listRates.iterator();
        while (iterator.hasNext()) {
            rate = iterator.next();
            rateInfo = new RateInfo(rate.getName(),rate.getUnit(),rate.getValue());
            rateInfoMap.put(rateInfo.getSymbolUnit(), rateInfo);
        }
    }

    public void addRateToCache(Rate rate){
        rateInfo = new RateInfo(rate.getName(),rate.getUnit(),rate.getValue());
        rateInfoMap.put(rateInfo.getSymbolUnit(), rateInfo);
    }

    public void updateRateInfoCache() {
        List<Rate> listRates = marketDataService.getRates();
        iterator = listRates.iterator();
        while (iterator.hasNext()) {
            rate = iterator.next();
            rateInfo = rateInfoMap.get(rate.getUnit());
            rateInfo.setLastPrice(rate.getValue());
        }
    }

    public void addOrUpdateCache(Rate rate){
        if(rateInfoMap.containsKey(rate.getUnit())){
            rateInfo = rateInfoMap.get(rate.getUnit());
            rateInfo.setLastPrice(rate.getValue());
        }else{
            addRateToCache(rate);
        }
    }

    public Map<String, RateInfo> getRateInfoMap() {
        return rateInfoMap;
    }
}
