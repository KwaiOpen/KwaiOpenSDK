package com.github.kwai.open.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public class SignUtils {

    public static String sign(Map<String, String> dataMap, String signSecret) {
        String signStr = genSignStr(dataMap);
        return DigestUtils.md5Hex(signStr + signSecret);
    }

    private static String genSignStr(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        data.keySet().stream().sorted()
            .filter(key -> !isBlank(key) && !isBlank(data.get(key)))
            .forEach(key -> {
                sb.append(key);
                sb.append("=");
                sb.append(data.get(key));
                sb.append("&");
            });
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    private static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    private static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String signS = "aac7542d1bd379bec1b0ef5c3bc69df6";
        Map<String, String> m = new HashMap<>();
        m.put("A", "123456");
        m.put("B", "456789");

        System.out.println(sign(m, signS));

    }

}
