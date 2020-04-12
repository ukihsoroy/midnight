package org.ko.analysis.core.help;

public final class GeneratorCodeUtils {

    public static String numberCode (int length) {
        return "" + (int)((Math.random() * 9 + 1) * Math.pow(10, length - 1));
    }

    public static String numberCode () {
        return numberCode(6);
    }

}
