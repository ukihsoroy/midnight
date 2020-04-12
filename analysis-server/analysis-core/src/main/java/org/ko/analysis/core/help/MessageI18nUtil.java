package org.ko.analysis.core.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageI18nUtil {

    private static MessageSource messageSource;

    @Autowired(required = true)
    public void intiMessageSource(MessageSource source) {
        MessageI18nUtil.messageSource = source;
    }

    public static String getI18nMessage(String msgCode, Object[] args) {
        return messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
    }
}
