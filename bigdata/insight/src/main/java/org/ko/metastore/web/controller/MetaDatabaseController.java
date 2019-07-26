package org.ko.metastore.web.controller;

import org.ko.metastore.support.Response;
import org.ko.metastore.web.domain.MetaDatabase;
import org.ko.metastore.web.service.MetaDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meta/database")
public class MetaDatabaseController {

    @Autowired
    private MetaDatabaseService metaDatabaseService;

    @PostMapping
    public Response save(@ModelAttribute MetaDatabase metaDatabase) {
        metaDatabaseService.save(metaDatabase);
        return new Response(true);
    }

    @GetMapping
    public Response<Iterable<MetaDatabase>> query() {
        return new Response<>(metaDatabaseService.query());
    }
}
