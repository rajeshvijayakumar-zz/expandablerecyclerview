package net.demo.healthifyme.utils;

/**
 * Created by rajesh5kumar on 16/7/16.
 */
public class StringUtils {
    public static String capitalize(String s) {
        if (s == null)
            return s;

        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static String formatData(String... params){

        return null;
    }
}
