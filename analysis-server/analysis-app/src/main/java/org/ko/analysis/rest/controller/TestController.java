package org.ko.analysis.rest.controller;

import org.ko.analysis.conf.api.Response;
import org.ko.analysis.store.ads.service.AdsDashboardService;
import org.ko.analysis.store.ods.service.ProduceService;
import org.ko.analysis.store.ads.domain.AdsDashboard;
import org.ko.analysis.store.ods.domain.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: TestController <br>
 * date: 2020/4/13 22:06 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@RestController
public class TestController {

    @Autowired private AdsDashboardService adsDashboardService;

    @Autowired private ProduceService produceService;

    @GetMapping("ads")
    public Response<List<AdsDashboard>> getAds() {
        return Response.ok(adsDashboardService.selectList(null));
    }

    @GetMapping("ods")
    public Response<List<Produce>> getOds() {
        return Response.ok(produceService.selectList(null));
    }

}
