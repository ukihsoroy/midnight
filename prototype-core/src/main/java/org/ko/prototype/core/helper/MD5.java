package org.ko.prototype.core.helper;

import org.apache.commons.codec.digest.DigestUtils;
import org.ko.prototype.core.type.Case;

public final class MD5 {

    public static String encrypt(String text) {
        return encrypt(text, Case.Input);
    }

    public static String encrypt(String text, Case c) {
        String value;
        if (c == Case.Input) {
            value = DigestUtils.md5Hex(text);
        } else if (c == Case.Lower) {
            value = DigestUtils.md5Hex(text.toLowerCase());
        } else {
            value = DigestUtils.md5Hex(text.toUpperCase());
        }

        return value;
    }

    public static String encrypt(String text, String salt) {
        return encrypt(text, salt, Case.Input);
    }

    public static String encrypt(String text, String salt, Case caze) {
        String s = salt == null ? "" : salt.trim();
        String value;
        if (caze == Case.Input) {
            value = encrypt(encrypt(text) + s);
        } else if (caze == Case.Lower) {
            value = encrypt(encrypt(text) + s.toLowerCase());
        } else {
            value = encrypt(encrypt(text) + s.toUpperCase());
        }

        return value;
    }


    private MD5() {}
}
