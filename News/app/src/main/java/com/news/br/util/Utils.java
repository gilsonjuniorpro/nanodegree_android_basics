package com.news.br.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gilsonjuniorpro on 5/4/17.
 */

public class Utils {

    public static String formatDate(String dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dateObject);
    }


    public static String formataDataComTimeZone(String dataString){
        Date data = new Date(dataString);

        DateFormat dt;
        if (dataString.endsWith("Z")) {
            dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        } else {
            dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        }

        String dataHoraRegistro = dt.format(dataString);

        return dataHoraRegistro;
    }
}
