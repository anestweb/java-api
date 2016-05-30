package br.projetointegrador.anestwebm.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class StringUtil {

    public static String toCamelCase(String str, boolean lowerCamelCase) {
        String[] parts = str.toLowerCase().split("_");
        for (int i = lowerCamelCase ? 1 : 0; i < parts.length; i++) {
            parts[i] = StringUtil.capitalize(parts[i]);
        }
        return StringUtil.join(parts);
    }

    public static String toCamelCase(String str) {
        return toCamelCase(str, false);
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String join(String[] words) {
        String joined = "";
        for (String w : words) {
            joined = joined + w;
        }
        return joined;
    }

    public static String formatCpf(String cpf) {
        // Remove formatação que possa já existir no cpf passado.
        cpf = cpf.replaceAll("[^0-9]", "");
        final String regex = "^([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$";
        return cpf.replaceAll(regex, "$1.$2.$3-$4");
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, "dd/MM/yyyy");
    }

    public static Date stringToDate(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        return stringToDate(date, "dd/MM/yyyy");
    }

    public static String sha256(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
