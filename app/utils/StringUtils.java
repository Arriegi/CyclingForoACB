package utils;

import controllers.MountainAndSprints;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 03/02/2015.
 */
public class StringUtils {

    public static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String get(Map<String, String[]> values, String key) {
        if (!values.containsKey(key)) {
            return null;
        } else {
            return values.get(key)[0];
        }
    }

    public static Integer getInt(Map<String, String[]> values, String key) {
        if (!values.containsKey(key)) {
            return null;
        } else {
            return Integer.parseInt(values.get(key)[0]);
        }
    }

    public static Date getDate(Map<String, String[]> values, String key) {
        if (!values.containsKey(key)) {
            return null;
        } else {
            return new Date(getLong(values,key));
        }
    }

    public static Long getLong(Map<String, String[]> values, String key) {
        if (!values.containsKey(key)) {
            return null;
        } else {
            return Long.parseLong(values.get(key)[0]);
        }
    }

    public static boolean allCyclistsExists(Map<String,String[]> values, int cyclistNumber) {
        boolean result = false;
        for(int i = 0;i<cyclistNumber;i++) {
            result = result || !values.containsKey(MountainAndSprints.CYCLIST + i);
        }
        return !result;
    }

    public static boolean allTeamsExists(Map<String,String[]> values) {
        boolean result = false;
        for(int i = 0;i<10;i++) {
            result = result || !values.containsKey(MountainAndSprints.TEAM + i);
        }
        return !result;
    }

    public static boolean allGCyclistsExists(Map<String,String[]> values) {
        boolean result = false;
        for(int i = 0;i<10;i++) {
            result = result || !values.containsKey(MountainAndSprints.GCYCLIST + i);
        }
        return !result;
    }

    public static boolean allRCyclistsExists(Map<String,String[]> values) {
        boolean result = false;
        for(int i = 0;i<3;i++) {
            result = result || !values.containsKey(MountainAndSprints.RCYCLIST + i);
        }
        return !result;
    }

    public static boolean allMCyclistsExists(Map<String,String[]> values) {
        boolean result = false;
        for(int i = 0;i<3;i++) {
            result = result || !values.containsKey(MountainAndSprints.MCYCLIST + i);
        }
        return !result;
    }
}
