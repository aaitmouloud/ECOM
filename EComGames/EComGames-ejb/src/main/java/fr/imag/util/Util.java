/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Classe d'util
 *
 * @author aaitmouloud
 */
public class Util {

    private static final DateFormat stdDF = DateFormat
            .getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    private static final String NULL_STRING = "null";

    public static String formatCalendar(Calendar calendar) {
        if (calendar == null) {
            return NULL_STRING;
        }
        return stdDF.format(calendar.getTime());
    }

    public static String hashMdp(String mdp) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(mdp.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

    }
}
