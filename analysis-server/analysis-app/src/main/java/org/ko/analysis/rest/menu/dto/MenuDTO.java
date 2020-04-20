package org.ko.analysis.rest.menu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.master.domain.Menu;
import org.ko.analysis.store.master.view.MenuMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends Menu {

    private List<MenuDTO> children = new ArrayList<>();

    private MenuMeta meta;

}

