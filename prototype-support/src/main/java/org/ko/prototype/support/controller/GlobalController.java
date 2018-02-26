package org.ko.prototype.support.controller;

import org.ko.prototype.support.type.AppCode;
import org.ko.prototype.support.view.View;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

    @GetMapping("timeout")
    public View timeout () {
        return new View(AppCode.SESSION_TIME_OUT);
    }
}
