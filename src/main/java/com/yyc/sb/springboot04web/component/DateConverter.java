package com.yyc.sb.springboot04web.component;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DateConverter implements Converter<String, Date> {
    private static final List<String> dateFormatList = new ArrayList<>(2);

    static {
        dateFormatList.add("yyyy-MM");
        dateFormatList.add("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(dateFormatList.get(0), source);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(dateFormatList.get(1), source);
        } else {
            throw new IllegalArgumentException("日期转换失败");
        }
    }

    private Date parseDate(String format, String dateStr) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            System.out.println("日期转换失败！");
        }
        return date;
    }
}
