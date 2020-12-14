package com.kuaishou.open.sdk.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-16
 */
public class ArrayUtils {

    public static List<byte[]> splitBytes(byte[] bytes, int size) {
        List<byte[]> result = new ArrayList<>();
        int start = 0;
        int end = size;
        while (end < bytes.length) {
            result.add(Arrays.copyOfRange(bytes, start, end));
            start = end;
            end = end + size;
        }
        if (start < bytes.length) {
            result.add(Arrays.copyOfRange(bytes, start, bytes.length));
        }
        return result;

    }
}
