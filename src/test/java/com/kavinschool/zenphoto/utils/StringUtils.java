package com.kavinschool.zenphoto.utils;

import java.text.DecimalFormat;

public class StringUtils {

    public static  String customFormat(String pattern, double value) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value);
    }

    public static Double usdToDouble(String value) {
        return  Double.parseDouble(value.substring(1).replaceAll(",",""));
    }

    /**
     * Simplify method class name string.
     *
     * @param className  the class name
     * @param methodName the method name
     * @return the string
     */
    public static String simplifyMethodClassName(final String className, final String methodName) {
        return simplifyMethodClassName(className, methodName, " @ ");
    }

    /**
     * Simplify method class name string.
     *
     * @param className  the class name
     * @param methodName the method name
     * @param separator  the separator
     * @return the string
     */
    public static String simplifyMethodClassName(final String className, final String methodName, final String separator) {
        String simplifyName = methodName + separator;
        final String[] classNameParts = className.split("\\.");
        final int length = classNameParts.length;
        System.out.println(length);
        if (length >= 3) {
            simplifyName += ".." + classNameParts[length - 3] + "." + classNameParts[length - 2] + "." + classNameParts[length - 1];
        } else {
            for (int i = 0; i < length; i++) {
                simplifyName = simplifyName.concat(classNameParts[i]);
                if (i != length - 1) {
                    simplifyName = simplifyName.concat(".");
                }
            }
        }
        return simplifyName;
    }


    /**
     * Is numeric boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNumeric(final String str) {
        try {
            @SuppressWarnings("unused") final double d = Double.parseDouble(str);
        } catch (final NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void writeLog(final String msg) {
        System.out.println(msg);
    }
}
