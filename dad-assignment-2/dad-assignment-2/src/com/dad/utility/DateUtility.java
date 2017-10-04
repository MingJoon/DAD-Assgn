package com.dad.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility methods for Date operations
 */
public class DateUtility {
    public DateUtility() {
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
