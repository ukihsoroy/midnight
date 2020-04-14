package org.ko.analysis.core.help;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 全局handler前日期统一处理
 */
@Component
public class DateConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private static final List<String> formats = new ArrayList<String>();
    static{
        formats.add("yyyy-MM");
        formats.add("yyyy-MM-dd");
        formats.add("yyyy-MM-dd hh:mm");
        formats.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        if(StringUtils.isBlank(source)){
            return null;
        }
        String value = source.trim();
        if(value.matches("^\\\\d{4}-\\\\d{1,2}$\"")){
            return parseDate(value, formats.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formats.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formats.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formats.get(3));
        }else {
            logger.error("Invalid date value '" + source + "'");
            return null;
        }
    }

    public Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            logger.error("Invalid date value '" + dateStr + "'");
        }
        return date;
    }
}
