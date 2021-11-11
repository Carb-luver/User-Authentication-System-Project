package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "https://api.coingecko.com/api/v3", value = "marketDataClient")
public interface MarketDataClient {

    @RequestMapping(method = RequestMethod.GET, value = "/coins/list")
    List<Rate> getRates();

}
