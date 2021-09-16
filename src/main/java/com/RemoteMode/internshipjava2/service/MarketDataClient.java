package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

@FeignClient(url = "https://api.coingecko.com/api/v3", value = "marketDataClient")
public interface MarketDataClient {

    @RequestMapping(method = RequestMethod.GET, value = "/coins/list")
    List<Rate> getRates();

//    @RequestMapping(method = RequestMethod.GET, value = "/simple/supported_vs_currencies")
//    List<String> getCurrencies();
}
