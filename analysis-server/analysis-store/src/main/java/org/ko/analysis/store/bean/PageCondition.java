package org.ko.analysis.store.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageCondition<T> extends Page<T> {

    public PageCondition() {
        super(1, 10);
    }

    public PageCondition(Long current, Long size) {
        super(current, size);
    }

}
