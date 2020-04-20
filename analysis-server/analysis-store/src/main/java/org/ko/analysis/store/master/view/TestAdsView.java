package org.ko.analysis.store.master.view;

import lombok.Data;

import java.io.Serializable;

/**
 * description: TestAdsView <br>
 * date: 2020/4/14 10:32 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Data
public class TestAdsView implements Serializable {

    private String name;
    private Double average;

}
