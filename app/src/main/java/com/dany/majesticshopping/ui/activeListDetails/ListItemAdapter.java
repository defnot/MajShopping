package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.ShoppingListApplication;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ServerValue;
import com.firebase.ui.FirebaseListAdapter;
import com.dany.majesticshopping.model.ListItem;

import java.util.HashMap;

/**
 * Created by Dany on 5/23/2016.
 */
public class ListItemAdapter extends FirebaseListAdapter<ListItem> {
    private MajesticShoppingList mShoppingList;
    private String mListId;

    public ListItemAdapter(Activity activity, Class<ListItem> modelClass, int modelLayout, Query ref, String listId) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
    }

    public void setShoppingList(MajesticShoppingList shoppingList) {
        this.mShoppingList = shoppingList;
        this.notifyDataSetChanged();
    }

    @Override
    protected void populateView(View view, ListItem item, int position) {
        ImageButton buttonRemoveItem = (ImageButton) view.findViewById(R.id.button_remove_item);
        TextView textViewMealItemName = (TextView) view.findViewById(R.id.text_view_active_list_item_name);

        textViewMealItemName.setText(item.getName());

        final String itemRemoveId = this.getRef(position).getKey();
        buttonRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity, R.style.CustomTheme_Dialog)
                        .setTitle(mActivity.getString(R.string.remove_item_option))
                        .setMessage(mActivity.getString(R.string.dialog_message_are_you_sure_remove_item))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                removeItem(itemRemoveId);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                /* Dismiss the dialog */
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void removeItem(String itemId) {
        Firebase databaseRef = new Firebase(Constants.UNIQUE_FIREBASE_URL);

        HashMap<String, Object> updatedRemoveItemMap = new HashMap<String, Object>();
        updatedRemoveItemMap.put("/" + Constants.LIST_ITEMS_LOCATION + "/"
                               + mListId + "/" + itemId, null);

        HashMap<String, Object> changedTimestampMap = new HashMap<>();
                changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        updatedRemoveItemMap.put("/" + Constants.ACTIVE_LISTS_LOCATION +
                                "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

        databaseRef.updateChildren(updatedRemoveItemMap);
    }
}
