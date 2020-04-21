package ${rootPackage}.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class Query${entityName}Condition<${entityName}> extends PageCondition<${entityName}> {


}