package io.alpha.help;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

public class DigestHelperTests {

    @Test void whenSha256Success() {
        String encode = DigestHelper.sha256("123");
        System.out.println(encode);
    }

}
