package com.lte.admin.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtils {
    public DateConvertUtils() {
    }

    public static Date parse(String dateString, String dateFormat) {
        return parse(dateString, dateFormat, Date.class);
    }

    public static <T extends Date> T parse(String dateString, String dateFormat, Class<T> targetResultType) {
        if(StringUtils.isEmpty(dateString)) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);

            try {
                long e = df.parse(dateString).getTime();
                Date t = (Date)targetResultType.getConstructor(new Class[]{Long.TYPE}).newInstance(new Object[]{Long.valueOf(e)});
                return (T) t;
            } catch (ParseException var7) {
                String errorInfo = "cannot use dateformat:" + dateFormat + " parse datestring:" + dateString;
                throw new IllegalArgumentException(errorInfo, var7);
            } catch (Exception var8) {
                throw new IllegalArgumentException("error targetResultType:" + targetResultType.getName(), var8);
            }
        }
    }

    public static String format(Date date, String dateFormat) {
        return date == null?null:(new SimpleDateFormat(dateFormat)).format(date);
    }
}
