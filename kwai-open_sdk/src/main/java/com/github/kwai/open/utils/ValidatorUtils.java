package com.github.kwai.open.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import com.github.kwai.open.KwaiOpenException;
import com.github.kwai.open.KwaiOpenResultCode;
import com.github.kwai.open.anotation.NotNull;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-14
 */
public class ValidatorUtils {

    public static void valid(Object object) throws KwaiOpenException {
        if (isEmpty(object)) {
            return;
        }
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);

                NotNull notNull = field.getAnnotation(NotNull.class);
                if (!isEmpty(notNull) && isEmpty(value)) {
                    throw new KwaiOpenException(KwaiOpenResultCode.INVALID_REQUEST, notNull.errorMsg());
                }
            } catch (KwaiOpenException e) {
                throw e;
            } catch (Exception e) {

            }
        }
    }


    private static boolean isEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }
        return false;
    }
}
