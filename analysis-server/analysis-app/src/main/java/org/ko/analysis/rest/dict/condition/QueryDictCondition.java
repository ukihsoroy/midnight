package org.ko.analysis.rest.dict.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDictCondition<Dict> extends PageCondition<Dict> {


}