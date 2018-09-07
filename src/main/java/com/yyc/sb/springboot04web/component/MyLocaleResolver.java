package com.yyc.sb.springboot04web.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    private static final String I18N_LANGUAGE_URL_PARAM = "l";
    private static final String I18N_LANGUAGE_SESSION = "i18n_language_session";

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String i18nLanguageURLParam = httpServletRequest.getParameter(I18N_LANGUAGE_URL_PARAM);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(i18nLanguageURLParam)) {
            String[] language = i18nLanguageURLParam.split("_");
            locale = new Locale(language[0], language[1]);

            // 将国际化语言保存到session
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        } else {
            // 如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = httpServletRequest.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
