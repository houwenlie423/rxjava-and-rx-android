package com.example.rxjavaandrxandroid.utils;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version UrlUtil, v 0.1 Tue 7/11/2023 4:25 PM by Houwen Lie
 */
public class UrlUtil {

    private UrlUtil() {
        //prevent outside instantiation
    }

    public static Map<String, String> splitUrlParam(String url) {
        Uri uri = Uri.parse(url);
        Set<String> argNames = uri.getQueryParameterNames();

        HashMap<String, String> mapParam = new HashMap<>();

        if (argNames != null) {
            for (String name : argNames) {
                mapParam.put(name, uri.getQueryParameter(name));
            }
        }

        return mapParam;
    }
}
