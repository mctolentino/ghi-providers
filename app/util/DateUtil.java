package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    public static Date today() {
        return Calendar.getInstance()
                       .getTime();
    }

    public static Date threeMonthsFromToday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }
}
