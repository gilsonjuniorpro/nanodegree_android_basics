package com.booklisting.br.util;

import java.text.DecimalFormat;

/**
 * Created by gilsonjuniorpro on 4/29/17.
 */

public final class Utils {

    public static String formatDoubleToString(double value) {
        DecimalFormat response = new DecimalFormat("0.0");
        return response.format(value);
    }
}
