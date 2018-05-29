package com.danielbernal.transactions.converter;

import com.danielbernal.transactions.domain.StatisticDo;
import com.danielbernal.transactions.dto.StatisticDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Converter from domain object to data transfer object
 * @author dbernalbazzani
 */
@Service
public class StatisticConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticConverter.class);

    /**
     * Converts from DO to DTO
     * @param statisticDo
     * @return the transformed object
     */
    public StatisticDto convertToDto (StatisticDo statisticDo) {

        LOGGER.info("Converting to Dto");
        StatisticDto statisticDto = new StatisticDto(statisticDo.getSum(), statisticDo.getAvg(), statisticDo.getMax(), statisticDo.getMin(), statisticDo.getCount());
        return  statisticDto;
    }

}
