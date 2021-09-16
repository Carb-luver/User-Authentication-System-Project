package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.model.Rate;
import com.RemoteMode.internshipjava2.model.RateInfo;
import com.RemoteMode.internshipjava2.service.MarketDataClient;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MarketDataService {

    MarketDataClient marketDataClient;

    @Getter
    Map<String, Rate> rateMap;
    Map<String, RateInfo> rateInfoMap;
    List<Rate> listRates;
    ArrayList<RateInfo> listRateInfo;
    RateCache rateCache;
    Iterator<Rate> iterator;
    Logger logger;

    public MarketDataService(MarketDataClient marketDataClient) {
        this.marketDataClient = marketDataClient;
    }

    public ArrayList<RateInfo> getRateInfo() throws IOException {
        if(rateCache.getRateInfoMap() == null) {
            rateMap = null;
            List<Rate> listRates = marketDataClient.getRates();
            iterator = listRates.iterator();
            while (iterator.hasNext()) {
                Rate rate = iterator.next();
                rateMap.put(rate.getUnit(), rate);
            }
            rateCache.storeRateInfo();
            rateInfoMap = rateCache.getRateInfoMap();
            for (RateInfo rateInfo:rateInfoMap.values()){
                listRateInfo.add(rateInfo);
            }
        }else{
            rateCache.updateRateInfoCache();
            rateInfoMap = rateCache.getRateInfoMap();
            for (RateInfo rateInfo:rateInfoMap.values()){
                listRateInfo.add(rateInfo);
            }
        }
        if(listRateInfo != null)
            logger.fine("Retrieved rates from coingecko successfuly.");
        else
            logger.log(Level.INFO, "Retrieved rates from coingecko unsuccessfuly.");
        return listRateInfo;
    }

    public List<Rate> getRates(){
        if(listRateInfo != null)
            logger.fine("Retrieved rates from coingecko successfuly.");
        else
            logger.log(Level.INFO, "Retrieved rates from coingecko unsuccessfuly.");
        return marketDataClient.getRates();
    }


}
