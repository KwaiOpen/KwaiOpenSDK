package com.github.kwai.open.utils;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.KwaiOpenResultCode;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class AESUtils {

    private static final String CBC_AES_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static Cipher cipher;

    public static byte[] decrypt(byte[] aesKey, byte[] iv, String base64EncodeData) throws KwaiOpenException {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            byte[] data = decodeBase64(base64EncodeData);
            Cipher cipher = getCipher();
            byte[] decryptBytes = decrypt(cipher, data, keySpec, ivSpec);
            return decryptBytes;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.SERVER_ERROR, "AES解密失败", e);
        }
    }

    private static byte[] decrypt(Cipher cipher, byte[] cipherData, SecretKeySpec keySpec, IvParameterSpec ivSpec)
        throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        if (cipherData.length == 0) {
            return cipherData;
        }
        cipher.init(DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(cipherData);
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (cipher == null) {
            synchronized (AESUtils.class) {
                if (cipher == null) {
                    cipher = Cipher.getInstance(CBC_AES_ALGORITHM);
                }
            }
        }
        return cipher;
    }
}
