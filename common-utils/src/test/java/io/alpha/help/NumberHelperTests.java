package io.alpha.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class NumberHelperTests {

    @Test void test1 () {
        assert NumberHelper.isNumber("1");
    }

    @Test void test2 () {
        assert !NumberHelper.isNumber("A");
    }

    @Test void test3 () {
        assert NumberHelper.isNumber("111111111111111111111111111111111111111111111111111111111111111111111111111");
    }

    @Test void test4 () {
        assert !NumberHelper.isNumber("12345a");
    }

    @Test void test5 () {
        assert !NumberHelper.isNumber("a123456");
    }

    @Test void test6 () {
        assert !NumberHelper.isNumber("123a456");
    }

    @Test void test7 () {
//        for (;;) {
//            NumberHelper.isNumber("1231456");
//        }
    }

}
