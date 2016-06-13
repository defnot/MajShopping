package com.dany.majesticshopping.utils;


import com.dany.majesticshopping.BuildConfig;
/**
 * Created by Dany on 5/17/2016.
 */
public final class Constants {

    public static final String UNIQUE_FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    public static final String LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";

    public static final String FIREBASE_PROPERTY_ITEM_NAME = "itemName";
    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";

    public static final String ACTIVE_LISTS_LOCATION = "activeLists";
    public static final String LIST_ITEMS_LOCATION = "shoppingListItems";

    public static final String ITEM_NAME = "itemName";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_LIST_NAME = "listName";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_USERS_SHOPPING = "usersShopping";
    public static final String FIREBASE_LOCATION_SHOPPING_LIST_ITEMS = "shoppingListItems";
    public static final String FIREBASE_URL_ACTIVE_LISTS = UNIQUE_FIREBASE_URL + "/" + ACTIVE_LISTS_LOCATION;

    public static final String KEY_LIST_NAME = "LIST_NAME";
    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String KEY_LIST_ID = "LIST_ID";

    public static final String ACTIVE_LISTS_LOCATION_RENAME_URL = UNIQUE_FIREBASE_URL + "/" + ACTIVE_LISTS_LOCATION;
    public static final String LIST_ITEMS_LOCATION_URL = UNIQUE_FIREBASE_URL + "/" + LIST_ITEMS_LOCATION;
    public static final String FIREBASE_URL_USERS = UNIQUE_FIREBASE_URL + "/" + "users";

    public static final String FIREBASE_PROPERTY_BOUGHT = "bought";
    public static final String FIREBASE_PROPERTY_BOUGHT_BY = "boughtBy";

    public static final String FIREBASE_URL_SHOPPING_LIST_ITEMS = UNIQUE_FIREBASE_URL + "/" + FIREBASE_LOCATION_SHOPPING_LIST_ITEMS;



    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_LIST_OWNER = "LIST_OWNER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";

    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";




}
