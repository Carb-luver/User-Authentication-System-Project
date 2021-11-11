package com.RemoteMode.internship2correction.controller;

import com.RemoteMode.internship2correction.model.RateInfo;
import com.RemoteMode.internship2correction.service.MarketDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RateController {

    MarketDataService marketDataService;

//    public RateController(MarketDataService marketDataService) {
//        this.marketDataService = marketDataService;
//    }

    @GetMapping("/initial")
    public List<RateInfo> getInitialRateInfo(){
        return marketDataService.getRateInfoList();
    }
}
