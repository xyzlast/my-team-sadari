package me.xyzlast.sadari.api.utils;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by ykyoon on 14. 11. 14.
 */
@Component
public class DateUtils {
    public Date convertFrom(String yyyyMmDd) {
        yyyyMmDd = yyyyMmDd.replace("-", "");
        int year = Integer.parseInt(yyyyMmDd.substring(0, 4));
        int month = Integer.parseInt(yyyyMmDd.substring(4, 6));
        int day = Integer.parseInt(yyyyMmDd.substring(6));

        return (new DateTime(year, month, day, 0, 0)).toDate();
    }
}
