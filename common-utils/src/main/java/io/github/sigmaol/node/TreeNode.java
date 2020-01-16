package io.github.sigmaol.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树型节点类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode {

	private String title;

	private Integer key;

	private Integer value;

}
