package io.github.sigmaol.bigdata.component.security;

import io.github.sigmaol.bigdata.component.security.sm.Hex;
import io.github.sigmaol.bigdata.component.security.sm.SM3Digest;

public class Sm3Util {
    public Sm3Util() {
    }

    public static String encrypt(String str) throws Exception {
        if(str != null && !"".equals(str)) {
            byte[] md = new byte[32];
            byte[] msg1 = str.getBytes();
            SM3Digest sm3 = new SM3Digest();
            sm3.update(msg1, 0, msg1.length);
            sm3.doFinal(md, 0);
            String ret = new String(Hex.encode(md));
            return ret;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(encrypt("sdfsfds"));
            System.out.println(encrypt("sdfsfds"));
        } catch (Exception var2) {
            ;
        }

    }
}
