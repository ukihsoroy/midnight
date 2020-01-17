package io.github.sigmaol.bigdata.common.security.sm;

import java.lang.*;
import java.lang.Iterable;

/**
 * Author: xiaohei
 * Date: 2019/9/20
 * Email: xiaohei.info@gmail.com
 * Host: xiaohei.info
 */
public interface StringList extends Iterable<String> {
    boolean add(String var1);

    String get(int var1);

    int size();

    String[] toStringArray();

    String[] toStringArray(int var1, int var2);
}
