package io.github.sigmaol.bigdata.component.security.sm;

import java.util.Iterator;

public interface Iterable<T> extends java.lang.Iterable<T> {
    @Override
    Iterator<T> iterator();
}
