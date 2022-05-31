package com.sharingplanner.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptUtil {
    public static String getEncrypt(String target) {
        String result = "";

        byte[] targetBytes = target.getBytes();
        byte[] bytes = new byte[targetBytes.length];

        System.arraycopy(targetBytes, 0, bytes, 0, targetBytes.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] digestedBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte data : digestedBytes) {
                sb.append(Integer.toString((data & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }

        return result;
    }

}
