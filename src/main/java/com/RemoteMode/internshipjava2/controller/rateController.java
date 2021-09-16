package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.model.RateInfo;
import com.RemoteMode.internshipjava2.service.MarketDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class rateController {

    MarketDataService marketDataService;

    public rateController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/initial")
    List<RateInfo> getInitialRateInfo() throws IOException {
        return marketDataService.getRateInfo();
    }
}
