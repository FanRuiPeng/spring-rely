package com.bmf.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String source) {
        Date value = null;
        if (null != source && source.trim().length() > 0 && !source.trim().equals("")) {
            try {
                value = format.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
