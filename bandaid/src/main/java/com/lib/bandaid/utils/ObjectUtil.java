package com.lib.bandaid.utils;

import androidx.annotation.NonNull;

import com.lib.bandaid.data.local.sqlite.utils.DateUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public final class ObjectUtil {


    public static Object str2SimpleObj(Type type, String s) {
        Object o = null;
        try {
            if (null == s) {
                return null;
            } else if ("".equals(s)) {
                return null;
            } else if (type == Integer.class || type == int.class) {
                o = Integer.parseInt(s);
            } else if (type == Float.class || type == float.class) {
                o = Float.parseFloat(s);
            } else if (type == Double.class || type == double.class) {
                o = Double.parseDouble(s);
            } else if (type == Long.class || type == long.class) {
                o = Long.parseLong(s);
            } else if (type == Short.class || type == short.class) {
                o = Short.parseShort(s);
            } else if (type == String.class) {
                o = s;
            } else if (type == Date.class) {
                o = DateUtil.strToDateTime(s);
            } else if (type == GregorianCalendar.class) {
                Date date = DateUtil.strToDateTime(s);
                if (date == null) {
                    o = null;
                } else {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    o = calendar;
                }
            }
        } catch (Exception e) {
            o = null;
            e.printStackTrace();
        }
        return o;
    }

    public static boolean baseTypeIsEqual(Object o1, Object o2, boolean nullEqual) {
        if (nullEqual) {
            if (o1 == null && o2 == null) {
                return true;
            } else if (o1 != null && o2 == null || o1 == null && o2 != null) {
                return false;
            } else {
                return baseTypeIsEqual(o1, o2);
            }
        } else {
            if (o1 == null && o2 == null) {
                return false;
            } else if ((o1 == null && o2 != null) || (o1 != null && o2 == null)) {
                return false;
            } else {
                return baseTypeIsEqual(o1, o2);
            }
        }
    }

    public static boolean baseTypeIsEqual(Object o1, Object o2) {
        if (o1 instanceof Number && o2 instanceof Number) {
            if (o1.toString().equals((o2.toString()))) {
                return true;
            }
        }
        if (o1 instanceof Integer && o2 instanceof Integer) {
            if (((Integer) o1).intValue() == ((Integer) o2).intValue()) {
                return true;
            }
        }
        if (o1 instanceof String && o2 instanceof String) {
            if (((String) o1).equals((String) o2)) {
                return true;
            }
        }
        if (o1 instanceof Long && o2 instanceof Long) {
            if (((Long) o1).longValue() == ((Long) o2).longValue()) {
                return true;
            }
        }
        if (o1 instanceof Double && o2 instanceof Double) {
            if (((Double) o1).doubleValue() == ((Double) o2).doubleValue()) {
                return true;
            }
        }
        if (o1 instanceof Short && o2 instanceof Short) {
            if (((Short) o1).shortValue() == ((Short) o2).shortValue()) {
                return true;
            }
        }
        if (o1 instanceof Float && o2 instanceof Float) {
            if (((Float) o1).floatValue() == ((Float) o2).floatValue()) {
                return true;
            }
        }
        if (o1 instanceof Boolean && o2 instanceof Boolean) {
            if ((Boolean) o1 == false && (Boolean) o2 == false) {
                return true;
            }
            return ((Boolean) o1 && (Boolean) o2);
        }
        return false;
    }


    public static boolean objFieldsIsEqual(Object o1, Object o2, Map<String, String> fields) {
        boolean isEqual = false;
        Object v1, v2;
        String f2;
        for (String f1 : fields.keySet()) {
            f2 = fields.get(f1);
            v1 = ReflectUtil.getValByNumberVar(o1, f1);
            v2 = ReflectUtil.getValByNumberVar(o2, f2);
            isEqual = baseTypeIsEqual(v1, v2, false);
            if (!isEqual) break;
        }
        return isEqual;
    }

    public static boolean baseTypeLike(Object baseObj, Object obj) {
        if (baseObj == null || obj == null) return false;
        if (baseObj instanceof String && obj instanceof String) {
            if (((String) baseObj).contains((String) obj)) {
                return true;
            }
        }
        return false;
    }

    public static int compareTo(Object o1, Object o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null && o2 != null) return 1;
        if (o1 != null && o2 == null) return -1;
        if (o1 instanceof Integer && o2 instanceof Integer) {
            return ((Integer) o1).compareTo(((Integer) o2));
        }
        if (o1 instanceof String && o2 instanceof String) {
            return ((String) o1).compareTo(((String) o2));
        }
        if (o1 instanceof Long && o2 instanceof Long) {
            return ((Long) o1).compareTo(((Long) o2));
        }
        if (o1 instanceof Double && o2 instanceof Double) {
            return ((Double) o1).compareTo(((Double) o2));
        }
        if (o1 instanceof Short && o2 instanceof Short) {
            return ((Short) o1).compareTo(((Short) o2));
        }
        if (o1 instanceof Float && o2 instanceof Float) {
            return ((Float) o1).compareTo(((Float) o2));
        }
        if (o1 instanceof Boolean && o2 instanceof Boolean) {
            return ((Boolean) o1).compareTo(((Boolean) o2));
        }
        return 0;
    }


    public static boolean isNumber(@NonNull Class<?> clazz) {
        if (clazz == Double.class || clazz == double.class) {
            return true;
        }
        if (clazz == Integer.class || clazz == int.class) {
            return true;
        }
        if (clazz == Long.class || clazz == long.class) {
            return true;
        }
        if (clazz == Short.class || clazz == short.class) {
            return true;
        }
        if (clazz == Byte.class || clazz == byte.class) {
            return true;
        }
        if (clazz == Float.class || clazz == float.class) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(Object o) {
        Number res = 0;
        if (o == null) return false;
        if (o instanceof Double) {
            return true;
        }
        if (o instanceof Integer) {
            return true;
        }
        if (o instanceof Long) {
            return true;
        }
        if (o instanceof Short) {
            return true;
        }
        if (o instanceof Byte) {
            return true;
        }
        if (o instanceof Float) {
            return true;
        }
        return false;
    }

    public static boolean isDate(Class<?> o) {
        if (o == GregorianCalendar.class
                || o == Date.class
                || o == java.sql.Date.class) {
            return true;
        }
        return false;
    }

    public static boolean isDate(Object o) {
        if (o instanceof GregorianCalendar
                || o instanceof Date
                || o instanceof java.sql.Date) {
            return true;
        }
        return false;
    }
}
