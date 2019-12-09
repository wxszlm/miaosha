package top.yxf.test;

import org.bouncycastle.util.encoders.Base64Encoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AES {
    public static void main(String[] args) {
        try {
            String content = "123456";
            String secretKey = "0606e3c0834d6afab2df078d9c82c967";
            byte[] encryptedContentBytes = encrypt(content, secretKey);
            String encryptedContent = parseBinaryToHexStr(encryptedContentBytes);
            System.out.println("加密前的文本:" + content);
            System.out.println("加密后的文本:" + encryptedContent);
            System.out.println("加密后的文本:" + new BASE64Encoder().encode(encryptedContentBytes));
            byte[] encryptedContentToBinaryStr = parseHexToBinaryStr(encryptedContent);
            byte[] decryptedContentBytes = decrypt(encryptedContentToBinaryStr, secretKey);
            String decryptedContent = new String(decryptedContentBytes);
            System.out.println("解密后的文本:" + decryptedContent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author ラピスラズリ(Dawn)
     * @date 2019年5月28日 下午2:56:42
     * @detail 16进制字符串转换2进制字符串
     */
    public static byte[] parseHexToBinaryStr(String hexStr) throws Exception {
        byte[] bytes = null;
        if(hexStr.length() < 1) {
            return bytes;
        }
        bytes = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            bytes[i] = (byte) (high * 16 + low);
        }
        return bytes;
    }

    /**
     * @author ラピスラズリ(Dawn)
     * @date 2019年5月28日 下午2:54:56
     * @detail 2进制字符串转换16进制字符串
     */
    public static String parseBinaryToHexStr(byte[] bytes) throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xff);
            if(hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @author ラピスラズリ(Dawn)
     * @date 2019年5月28日 下午3:30:33
     * @detail 解密
     */
    public static byte[] decrypt(byte[] content, String secretKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(secretKey.getBytes()));
        SecretKey generatedSecretKey = keyGenerator.generateKey();
        byte[] encodedBytes = generatedSecretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(encodedBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] result = cipher.doFinal(content);
        return result;
    }

    /**
     * @author ラピスラズリ(Dawn)
     * @date 2019年5月28日 下午2:55:25
     * @detail aes加密
     */
    public static byte[] encrypt(String content, String secretKey) throws Exception {
        // 创建AES的Key生产者
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 利用用户密码作为随机数初始化出
        // 128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，
        //只要种子相同，序列就一样，所以解密只要有password就行
        keyGenerator.init(128, new SecureRandom(secretKey.getBytes()));
        // 根据用户密码，生成一个密钥
        SecretKey generatedSecretKey = keyGenerator.generateKey();
        // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
        byte[] encodedBytes = generatedSecretKey.getEncoded();
        // 转换为AES专用密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(encodedBytes, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        byte[] contentBytes = content.getBytes("utf-8");
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //加密
        byte[] result = cipher.doFinal(contentBytes);
        return result;
    }
}
