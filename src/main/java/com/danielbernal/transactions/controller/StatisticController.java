package com.danielbernal.transactions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielbernal.transactions.converter.StatisticConverter;
import com.danielbernal.transactions.dto.StatisticDto;
import com.danielbernal.transactions.service.StatisticService;

/**
 * Controller to handle http requests regarding statistics
 * @author dbernalbazzani
 */
@RestController
public class StatisticController {


    @Autowired
    private StatisticService statisticService;

    @Autowired
    private StatisticConverter statisticConverter;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    /**
     * Request to obtain the statistics of the transactions
     * @return statistics
     */
    @RequestMapping(path = "/statistics", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public StatisticDto obtainStatistics () {

    	LOGGER.info("Request to obtain statistics");
        return statisticConverter.convertToDto(statisticService.obtainStatistics());
    }
}
