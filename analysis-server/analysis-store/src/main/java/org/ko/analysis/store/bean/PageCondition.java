package org.ko.analysis.store.bean;


import com.baomidou.mybatisplus.plugins.Page;

public class PageCondition<T> extends Page<T> {

    public PageCondition() {
        super(1, 10);
    }

    public PageCondition (Integer current, Integer size) {
        super(current, size);
    }

}
