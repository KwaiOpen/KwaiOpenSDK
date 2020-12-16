package com.github.kwai.open.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.KwaiOpenResultCode;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-16
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T readValue(String jsonValue, Class<T> clazz) throws KwaiOpenException {
        try {
            T result = objectMapper.readValue(jsonValue, clazz);
            return result;
        } catch (Exception e) {
            throw new KwaiOpenException(KwaiOpenResultCode.SDK_ERROR, "JSON PARSE ERROR, json str:[" + jsonValue + "]", e);
        }

    }
}
