package com.dany.majesticshopping.utils;


import com.dany.majesticshopping.BuildConfig;
/**
 * Created by Dany on 5/17/2016.
 */
public final class Constants {

    public static final String UNIQUE_FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";

    public static final String ACTIVE_LISTS_LOCATION = "activeLists";
    public static final String LIST_ITEMS_LOCATION = "shoppingListItems";

    public static final String ITEM_NAME = "itemName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";

    public static final String ACTIVE_LISTS_LOCATION_RENAME_URL = UNIQUE_FIREBASE_URL + "/" + ACTIVE_LISTS_LOCATION;
    public static final String LIST_ITEMS_LOCATION_URL = UNIQUE_FIREBASE_URL + "/" + LIST_ITEMS_LOCATION;




}
