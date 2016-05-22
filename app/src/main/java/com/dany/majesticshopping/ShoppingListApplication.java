package com.dany.majesticshopping;

import com.firebase.client.Firebase;

public class ShoppingListApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}