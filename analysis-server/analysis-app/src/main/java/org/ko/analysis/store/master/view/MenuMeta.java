package org.ko.analysis.store.master.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuMeta implements Serializable {

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

}
