package io.github.sigmaol.help;

import org.junit.jupiter.api.Test;

public class DigestHelperTests {

    @Test void whenSha256Success() {
        String encode = DigestHelper.sha256("123");
        System.out.println(encode);
    }

}
