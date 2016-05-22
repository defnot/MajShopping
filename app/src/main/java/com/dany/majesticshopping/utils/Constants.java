package com.dany.majesticshopping.utils;


import com.dany.majesticshopping.BuildConfig;
/**
 * Created by Dany on 5/17/2016.
 */
public final class Constants {

    public static final String UNIQUE_FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";

    public static final String ACTIVE_LISTS = "activeLists";
    public static final String ACTIVE_LISTS_LOCATION = UNIQUE_FIREBASE_URL + "/" + ACTIVE_LISTS;

    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";

    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";

    public static final String KEY_LIST_ID = "LIST_ID";



}
