package com.example;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.*;

public class AESApp {
    private static SecretKeySpec secretkey;
    private static byte[] key;

    // setkey
    public static void setKey(String keyInput) {
        try {
            key = keyInput.getBytes("UTF-8");
            /*
             * Checksum - error detection method - adds up the bytes, checks the sum to make
             * sure the data matches
             */
            /* Hash function - is a function to produce checksum */
            /* Hash value - a numeric value of fixed length that uniquely identifies data */
            /*
             * Message Digest - fixed size numeric representation of message content,
             * computed by hash function
             */
            /*
             * In Java MessageDigect class provides functionality for digest using SHA-1 or
             * SHA - 256
             */
            /* SHA - Secure Hashing Algorithm */
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            // user passes key into setkey, which is used to generate bytes, then those
            // bytes are used to create digest, which is equated to the key variable
            key = Arrays.copyOf(key, 16); // creates new copy with only 16 bytes. New key
            secretkey = new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
    }

    // encryption method
    public static String Encrypt(String cleartext, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(cleartext.getBytes("UTF-8")));

        } catch (Exception e) {
            return new String("There has been an error! Check your key or message.");
        }
    }

    // decryption method
    public static String Decrypt(String ciphertext, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));

        } catch (Exception e) {
            return new String("There has been an error! Check your key or message.");
        }
    }
}