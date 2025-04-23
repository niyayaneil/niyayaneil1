package com.leadtrans.dictservice.common.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static boolean isNumber(String str){
        return str!=null && str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNonNegativeNumber(String str) {
        return str != null && str.matches("\\d+(\\.\\d+)?([eE][+]?\\d+)?");
        // 匹配非负整数、小数或科学计数法表示的非负数
    }
    public static boolean isPositiveInteger(String str) {
        return str != null && str.matches("^[1-9]\\d*$");
    }
    public static String removeTrailingZeros(String numberStr) {
        if (numberStr == null) return null;
        try {
            return new BigDecimal(numberStr).stripTrailingZeros().toPlainString();
        } catch (NumberFormatException e) {
            return numberStr;
        }
    }
    public static String stripTrailingZerosString(BigDecimal bigDecimal) {
        if (bigDecimal == null) return null;
        return bigDecimal.stripTrailingZeros().toPlainString();
    }
    public static BigDecimal stripTrailingZeros(BigDecimal bigDecimal) {
        if (bigDecimal == null) return null;
        return bigDecimal.stripTrailingZeros();
    }

    public static void main(String[] args) {
        boolean a = NumberUtils.isNonNegativeNumber("0");
        System.out.println(a);
    }
}
