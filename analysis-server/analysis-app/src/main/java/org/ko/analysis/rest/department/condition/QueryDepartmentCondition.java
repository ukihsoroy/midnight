package org.ko.analysis.rest.department.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDepartmentCondition<Department> extends PageCondition<Department> {


}