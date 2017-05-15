package com.inventory.br.util;

import com.inventory.br.data.CupContract.CupEntry;


/**
 * Created by gilsonjuniorpro on 5/8/17.
 */

public class Utils {


    public static String getMaterial(int material) {
        String m;
        switch (material) {
            case CupEntry.MATERIAL_PLASTIC:
                m = "PLASTIC";
                break;
            case CupEntry.MATERIAL_METAL:
                m = "METAL";
                break;
            default:
                m = "CERAMIC";
                break;
        }

        return m;
    }
}
