package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.model.Rate;
import com.RemoteMode.internship2correction.model.RateInfo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;
import java.util.List;

@EnableScheduling
public class MarketDataService {

    MarketDataClient marketDataClient;
    RateInfoCache rateInfoCache;
    SimpMessagingTemplate simpMessagingTemplate;

    public MarketDataService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public MarketDataService(MarketDataClient marketDataClient) {
        this.marketDataClient = marketDataClient;
    }

    @Scheduled(fixedDelay = 5000)
    @SubscribeMapping("/topic/updateRates")
    public void getRateInfoUpdates(){
        simpMessagingTemplate.convertAndSend("/topics/updateRates", getRateInfoList());
    }

    public List<RateInfo> getRateInfoList(){
        List<Rate> rateList = (List<Rate>) marketDataClient.getRates();
        Iterator rateListIterator = rateList.listIterator();
        while (rateListIterator.hasNext()){
            Rate rate = (Rate) rateListIterator.next();
            rateInfoCache.addOrUpdateCache(rate);
        }
        return (List<RateInfo>) rateInfoCache.getRateInfoCache().values();
    }
}
