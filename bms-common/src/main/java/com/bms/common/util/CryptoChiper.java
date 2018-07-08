package com.bms.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.MessageFormat;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

import com.bms.common.BmsException;

public class CryptoChiper {

    private static final String CHARSET = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEM = "DESede";
    private String ENCRYPTION_KEY = "123987@SeLJan18&IsSparta";

    private Cipher cipher;
    private SecretKey key;

    public CryptoChiper() throws BmsException {
    	KeySpec keySpec;
		try {
			keySpec = new DESedeKeySpec(ENCRYPTION_KEY.getBytes(CryptoChiper.CHARSET));
			SecretKeyFactory factory = SecretKeyFactory.getInstance(CryptoChiper.DESEDE_ENCRYPTION_SCHEM);
	        key = factory.generateSecret(keySpec);
	        cipher = Cipher.getInstance(CryptoChiper.DESEDE_ENCRYPTION_SCHEM);
		} catch (InvalidKeyException 
				| UnsupportedEncodingException 
				| NoSuchAlgorithmException 
				| InvalidKeySpecException 
				| NoSuchPaddingException e) {
			throw new BmsException(e);
		}
    }

    public String encrypt(String unencryptedString) {
    	if(StringUtils.isNullEmptyOrWhiteSpace(unencryptedString)) {
    		return unencryptedString;
    	}
    	
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(CryptoChiper.CHARSET);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
    	if(StringUtils.isNullEmptyOrWhiteSpace(encryptedString)) {
    		return encryptedString;
    	}
    	
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }


    public static void main(String args []) throws Exception
    {
    	CryptoChiper cc= new CryptoChiper();

        String target="Sel@1234";
        String encrypted=cc.encrypt(target);
        String decrypted=cc.decrypt(encrypted);

        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }

}