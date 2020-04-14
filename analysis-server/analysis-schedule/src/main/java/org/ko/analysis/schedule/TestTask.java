package org.ko.analysis.schedule;

import org.ko.analysis.core.help.JsonHelper;
import org.ko.analysis.store.ads.domain.AdsDashboard;
import org.ko.analysis.store.ads.repository.AdsDashboardRepository;
import org.ko.analysis.store.ads.service.AdsDashboardService;
import org.ko.analysis.store.ads.view.TestAdsView;
import org.ko.analysis.store.ods.domain.Produce;
import org.ko.analysis.store.ods.repository.ProduceRepository;
import org.ko.analysis.store.ods.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: TestTask <br>
 * date: 2020/4/14 10:21 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Component
public class TestTask {

    @Autowired private ProduceService produceService;

    @Autowired private AdsDashboardService adsDashboardService;

    @Scheduled(cron = "1 * * * * *")    //单位：毫秒
    public void compute () {
        List<Produce> produces = produceService.selectList(null);
        double asDouble = produces.stream().mapToInt(Produce::getId).average().getAsDouble();

        AdsDashboard adsDashboard = new AdsDashboard();
        adsDashboard.setDashId("test_dash");
        adsDashboard.setBoardId("test_board");

        TestAdsView view = new TestAdsView();
        view.setName("test ads view.");
        view.setAverage(asDouble);

        adsDashboard.setData(JsonHelper.toJson(view));

        adsDashboardService.insert(adsDashboard);
    }

}
