package io.github.sigmaol.bigdata.component.security;

import io.github.sigmaol.bigdata.component.security.common.Strings;
import io.github.sigmaol.help.Base64Helper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String IV_STRING = "XINYAN-AES000000";

    public AESUtil() {
    }

    public static String encrypt(String source, String key) {
        try {
            if(key != null && !"".equals(source) && source != null) {
                byte[] e = produceKey(key).getBytes();
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                byte[] initParam = "XINYAN-AES000000".getBytes();
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, skeySpec, ivParameterSpec);
                byte[] encrypted = cipher.doFinal(source.getBytes(Charset.forName("UTF-8")));
                return Base64Helper.encodeToString(encrypted);
            } else {
                return null;
            }
        } catch (Exception var8) {
            throw new SecurityException("AES加密失败", var8);
        }
    }

    public static String decrypt(String source, String key) {
        try {
            if(key != null && !"".equals(source) && source != null) {
                byte[] e = produceKey(key).getBytes();
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                byte[] initParam = "XINYAN-AES000000".getBytes();
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, skeySpec, ivParameterSpec);
                byte[] sourceBytes = Base64Helper.decodeFromString(source);
                byte[] original = cipher.doFinal(sourceBytes);
                return new String(original, Charset.forName("UTF-8"));
            } else {
                return null;
            }
        } catch (Exception var9) {
            throw new SecurityException("AES解密失败", var9);
        }
    }

    public static String produceKey(String key) {
        try {
            return key.length() < 16? Strings.padStart(key, 16, '0'):(key.length() > 16?key.substring(0, 16):key);
        } catch (Exception var2) {
            throw new SecurityException("AES生成密钥失败", var2);
        }
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("123456", "-AES000001");
        System.out.println("encrypt:" + encrypt);
        String decrypt = decrypt(encrypt, "-AES000001");
        System.out.println("decrypt:" + decrypt);
    }
}
