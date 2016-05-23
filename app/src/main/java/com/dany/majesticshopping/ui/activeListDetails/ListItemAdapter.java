package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Activity;
import android.view.View;

import com.dany.majesticshopping.ShoppingListApplication;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.dany.majesticshopping.model.ListItem;
/**
 * Created by Dany on 5/23/2016.
 */
public class ListItemAdapter extends FirebaseListAdapter<ListItem> {

    public ListItemAdapter(Activity activity, Class<ListItem> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, ListItem item) {
        // this is where we populate the view
    }

    private void removeItem() {

    }
}
