package com.danielbernal.transactions.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static Long converToTimeStamp(LocalDateTime localDateTime) {
    	
        return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

}
