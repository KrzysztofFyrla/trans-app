package pl.trans.app.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
    public static Date converterToDate(LocalDate localDate){
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate converterToLocalDate(Date date){
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
