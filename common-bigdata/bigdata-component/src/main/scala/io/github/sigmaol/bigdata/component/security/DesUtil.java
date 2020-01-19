package io.github.sigmaol.bigdata.component.security;

import io.github.sigmaol.help.Base64Helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DesUtil {
    private static final String DES = "DES";
    private static final String ENCODE = "GBK";

    public DesUtil() {
    }

    public static String encrypt(String data, String key) {
        try {
            key = String.format("%8s", new Object[]{key}).replaceAll(" ", "0");
            byte[] e = encrypt(data.getBytes("GBK"), key.getBytes("GBK"));
            String strs = Base64Helper.encodeToString(e);
            return strs;
        } catch (Exception var4) {
            throw new SecurityException("DES加密失败", var4);
        }
    }

    public static String decrypt(String data, String key) {
        try {
            key = String.format("%8s", new Object[]{key}).replaceAll(" ", "0");
            if(data == null) {
                return null;
            } else {
                byte[] buf = Base64Helper.decodeFromString(data);
                byte[] bt = decrypt(buf, key.getBytes("GBK"));
                return new String(bt, "GBK");
            }
        } catch (Exception var5) {
            throw new SecurityException("DES解密失败", var5);
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecureRandom e = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey, e);
            return cipher.doFinal(data);
        } catch (Exception var7) {
            throw new SecurityException("DES加密失败", var7);
        }
    }

    private static byte[] decrypt(byte[] data, byte[] key) {
        try {
            SecureRandom e = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, securekey, e);
            return cipher.doFinal(data);
        } catch (Exception var7) {
            throw new SecurityException("DES解密失败", var7);
        }
    }

    public static void main(String[] args) {
        String enCode = encrypt("11808151957525530202101", "1123111213132132");
        System.out.println(enCode);
        String deCode = decrypt(enCode, "1123111213132132");
        System.out.println(deCode);
    }
}
