package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.model.Rate;
import com.RemoteMode.internship2correction.model.RateInfo;
import com.RemoteMode.internship2correction.model.StartPrice;

import java.util.Map;

public class RateInfoCache {

    private Map<String, RateInfo> rateInfoCache;
    StartPrice startPrice;

    public void addRateToRateInfoCache(Rate rate) {
        if (!rateInfoCache.containsKey(rate.getName())) {
            startPrice = new StartPrice(rate.getName(), rate.getValue());
            rateInfoCache.put(rate.getName(), new RateInfo(rate.getName(), rate.getUnit(), rate.getValue(), rate));
        }
    }

    public Map<String, RateInfo> getRateInfoCache() {
        return rateInfoCache;
    }

    public void updateRateInfoCache(Rate rate){
        if(rateInfoCache.containsKey(rate.getName())){
            RateInfo rateInfo = rateInfoCache.get(rate.getName());
            rateInfo.setLastPrice(rate.getValue());
            rateInfoCache.replace(rate.getName(), rateInfo);
        }
    }

    public void addOrUpdateCache(Rate rate){
        if(rateInfoCache.containsKey(rate.getName())){
            updateRateInfoCache(rate);
        }else{
            addRateToRateInfoCache(rate);
        }
    }

}
