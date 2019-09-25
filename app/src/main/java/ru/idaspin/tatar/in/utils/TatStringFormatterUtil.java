package ru.idaspin.tatar.in.utils;

/**
 * Created by idaspin.
 * Date: 9/11/2017
 * Time: 11:54 PM
 */
public class TatStringFormatterUtil {
    public static String toHtml(String query) {
        String mQuery = query;
        mQuery = mQuery.replaceAll("1", "&#1257;");
        mQuery = mQuery.replaceAll("ә", "&#1257;");
        mQuery = mQuery.replaceAll("Ә", "&#1257;");
        mQuery = mQuery.replaceAll("2", "&#1241;");
        mQuery = mQuery.replaceAll("Ө", "&#1241;");
        mQuery = mQuery.replaceAll("ө", "&#1241;");
        mQuery = mQuery.replaceAll("3", "&#1175;");
        mQuery = mQuery.replaceAll("Ү", "&#1175;");
        mQuery = mQuery.replaceAll("ү", "&#1175;");
        mQuery = mQuery.replaceAll("4", "&#1187;");
        mQuery = mQuery.replaceAll("Җ", "&#1187;");
        mQuery = mQuery.replaceAll("җ", "&#1187;");
        mQuery = mQuery.replaceAll("5", "&#1199;");
        mQuery = mQuery.replaceAll("Ң", "&#1199;");
        mQuery = mQuery.replaceAll("ң", "&#1199;");
        mQuery = mQuery.replaceAll("6", "&#1211;");
        mQuery = mQuery.replaceAll("Һ", "&#1211;");
        mQuery = mQuery.replaceAll("һ", "&#1211;");
        return mQuery;
    }

    public static String fromHtml(String tat) {
        String mQuery = tat;
        mQuery = mQuery.replaceAll("&#1241;", "ә");
        mQuery = mQuery.replaceAll("&#1256;", "ө");
        mQuery = mQuery.replaceAll("&#1198;", "ү");
        mQuery = mQuery.replaceAll("&#1174;", "җ");
        mQuery = mQuery.replaceAll("&#1187;", "ң");
        mQuery = mQuery.replaceAll("&#1211;", "һ");
        mQuery = mQuery.replaceAll("&#1240;", "ә");
        mQuery = mQuery.replaceAll("&#1247;", "ө");
        mQuery = mQuery.replaceAll("&#1199;", "ү");
        mQuery = mQuery.replaceAll("&#1175;", "җ");
        mQuery = mQuery.replaceAll("&#1211;", "ң");
        mQuery = mQuery.replaceAll("&#1210;", "һ");
        mQuery = mQuery.replaceAll("&#1241", "ә");
        mQuery = mQuery.replaceAll("&#1256", "ө");
        mQuery = mQuery.replaceAll("&#1198", "ү");
        mQuery = mQuery.replaceAll("&#1174", "җ");
        mQuery = mQuery.replaceAll("&#1187", "ң");
        mQuery = mQuery.replaceAll("&#1211", "һ");
        mQuery = mQuery.replaceAll("&#1240", "ә");
        mQuery = mQuery.replaceAll("&#1247", "ө");
        mQuery = mQuery.replaceAll("&#1199", "ү");
        mQuery = mQuery.replaceAll("&#1175", "җ");
        mQuery = mQuery.replaceAll("&#1211", "ң");
        mQuery = mQuery.replaceAll("&#1210", "һ");
        return mQuery;
    }
}
