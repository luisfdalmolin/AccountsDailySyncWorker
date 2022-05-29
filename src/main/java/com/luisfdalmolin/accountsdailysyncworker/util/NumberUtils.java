package com.luisfdalmolin.accountsdailysyncworker.util;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat();

    static {
        decimalFormat.setMinimumFractionDigits(2);
    }

    public static Number parseValue(String value) throws ParseException {
        return decimalFormat.parse(value);
    }
}
