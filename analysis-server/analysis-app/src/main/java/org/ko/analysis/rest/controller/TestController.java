package org.ko.analysis.rest.controller;

import org.ko.analysis.conf.api.Response;
import org.ko.analysis.store.master.service.AdsDashboardService;
import org.ko.analysis.store.mpp.service.ProduceService;
import org.ko.analysis.store.master.domain.AdsDashboard;
import org.ko.analysis.store.mpp.domain.Produce;
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

    @Autowired private AdsDashboardService dashboardService;

    @Autowired private ProduceService produceService;

    @GetMapping("dashboard")
    public Response<List<AdsDashboard>> getAds() {
        return Response.ok(dashboardService.list());
    }

    @GetMapping("mpp")
    public Response<List<Produce>> getOds() {
        return Response.ok(produceService.list());
    }

}
