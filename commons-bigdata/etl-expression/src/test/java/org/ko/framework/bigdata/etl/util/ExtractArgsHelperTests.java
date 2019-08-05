package org.ko.framework.bigdata.etl.util;

import org.junit.jupiter.api.Test;

public class ExtractArgsHelperTests {

    @Test void whenParseLengthSuccess() {
        String[] keys = ExtractArgsHelper.parse("81, 2, 3, 4");
        assert keys.length == 4;
    }
}
