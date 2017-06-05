package com.ray.lib;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Author      : leixing
 * Date        : 2017-06-05
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class JsonBuilder {
    public static String toJson(Object o) {
        return toJson(o, null, false, 0, true);
    }

    private static String toJson(Object o, StringBuilder stringBuilder, boolean allowNullFiled, int tabDepth, boolean isLast) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder();
        }

        if (o == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("{").append('\n');

            Field[] fields = o.getClass().getDeclaredFields();
            if (fields != null) {
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    boolean isLastField = i == fields.length - 1;
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }

                    Object value = null;
                    try {
                        value = f.get(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    String name = f.getName();

                    if (value == null) {
                        if (allowNullFiled) {
                            stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append("null");
                            if (!isLastField) {
                                stringBuilder.append(',');
                            }
                            stringBuilder.append('\n');
                        }
                    } else if (isPrimitiveType(value.getClass())) {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append(value);
                        if (!isLastField) {
                            stringBuilder.append(',');
                        }
                        stringBuilder.append('\n');
                    } else if (value.getClass() == String.class) {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append('\"').append(value).append('\"');
                        if (!isLastField) {
                            stringBuilder.append(',');
                        }
                        stringBuilder.append('\n');
                    } else if (value.getClass().isArray()) {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append("[").append('\n');
                        Object[] array = (Object[]) value;
                        for (int j = 0; j < array.length; j++) {
                            stringBuilder.append(buildTabs(tabDepth + 2));
                            toJson(array[j], stringBuilder, allowNullFiled, tabDepth + 2, j == array.length - 1);
                        }
                        stringBuilder.append(buildTabs(tabDepth + 1)).append(']');
                        if (!isLastField) {
                            stringBuilder.append(',');
                        }
                        stringBuilder.append('\n');
                    } else if (value instanceof Collection) {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append("[").append('\n');
                        Collection c = (Collection) value;
                        Iterator iterator = c.iterator();
                        while (iterator.hasNext()) {
                            Object elem = iterator.next();
                            stringBuilder.append(buildTabs(tabDepth + 2));
                            toJson(elem, stringBuilder, allowNullFiled, tabDepth + 2, !iterator.hasNext());
                        }
                        stringBuilder.append(buildTabs(tabDepth + 1)).append(']');
                        if (!isLastField) {
                            stringBuilder.append(',');
                        }
                        stringBuilder.append('\n');
                    } else if (value instanceof Map) {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":").append("{").append('\n');
                        Map<?, ?> m = (Map) value;
                        Set<? extends Map.Entry<?, ?>> entries = m.entrySet();
                        Iterator<? extends Map.Entry<?, ?>> iterator = entries.iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<?, ?> entry = iterator.next();
                            Object entryKey = entry.getKey();
                            Object entryValue = entry.getValue();
                            stringBuilder.append(buildTabs(tabDepth + 2)).append('\"').append(entryKey.toString()).append('\"').append(":");
                            toJson(entryValue, stringBuilder, allowNullFiled, tabDepth + 2, !iterator.hasNext());
                        }
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('}');
                        if (!isLastField) {
                            stringBuilder.append(',');
                        }
                        stringBuilder.append('\n');
                    } else {
                        stringBuilder.append(buildTabs(tabDepth + 1)).append('\"').append(name).append('\"').append(":");
                        toJson(value, stringBuilder, allowNullFiled, tabDepth + 1, isLastField);
                    }
                }
            }

            stringBuilder.append(buildTabs(tabDepth)).append("}");
            if (!isLast) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    /**
     * @see java.lang.Boolean#TYPE
     * @see java.lang.Character#TYPE
     * @see java.lang.Byte#TYPE
     * @see java.lang.Short#TYPE
     * @see java.lang.Integer#TYPE
     * @see java.lang.Long#TYPE
     * @see java.lang.Float#TYPE
     * @see java.lang.Double#TYPE
     * @see java.lang.Void#TYPE
     */
    private static boolean isPrimitiveType(Class<?> cls) {
        return cls == boolean.class || cls == Boolean.class
                || cls == char.class || cls == Character.class
                || cls == byte.class || cls == Byte.class
                || cls == short.class || cls == Short.class
                || cls == int.class || cls == Integer.class
                || cls == long.class || cls == Long.class
                || cls == float.class || cls == Float.class
                || cls == double.class || cls == Double.class
                || cls == void.class || cls == Void.class;
    }

    private static String buildTabs(int num) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < num; i++) {
            stringBuilder.append('\t');
        }

        return stringBuilder.toString();
    }
}
