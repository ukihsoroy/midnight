package org.ko.analysis.conf.security.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

public class ExpiredSessionStrategyImpl implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write("并发登录");
    }
}