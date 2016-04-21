package br.projetointegrador.anestwebm.util;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class StringUtils {

    public static String toCamelCase(String str, boolean lowerCamelCase) {
        String[] parts = str.toLowerCase().split("_");
        for (int i = lowerCamelCase ? 1 : 0; i < parts.length; i++) {
            parts[i] = StringUtils.capitalize(parts[i]);
        }
        return StringUtils.join(parts);
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

}
