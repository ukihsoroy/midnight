package org.ko.analysis.core.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * controller中method只能传对象而非数组，列表等集合
 * @param <E>
 */
@Getter
@Setter
@ToString
public class EncapsulateList<E> implements Serializable {

    private List<E> list;

    private Set<E> set;

}
