package com.dany.majesticshopping.utils;

import android.content.Context;

import com.dany.majesticshopping.model.MajesticShoppingList;

import java.text.SimpleDateFormat;

public class Utils {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context mContext = null;

    public Utils(Context con) {
        mContext = con;
    }

    public static boolean checkIfOwner(MajesticShoppingList shoppingList, String currentUserEmail) {
        return (shoppingList.getOwner() != null &&
                shoppingList.getOwner().equals(currentUserEmail));
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

}
