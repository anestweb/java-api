package br.projetointegrador.anestwebm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
