package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.ListItem;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dany on 5/19/2016.
 */
public class AddListItemDialogFragment extends EditListDialogFragment {

    public static AddListItemDialogFragment newInstance(MajesticShoppingList shoppingList, String listId) {
        AddListItemDialogFragment addListItemDialogFragment = new AddListItemDialogFragment();

        Bundle bundle = newInstanceHelper(shoppingList, R.layout.dialog_add_item, listId);
        addListItemDialogFragment.setArguments(bundle);

        return addListItemDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.createDialogHelper(R.string.positive_button_add_list_item);
    }

    /**
     * Adds new item to the current shopping list
     */
    @Override
    protected void doListEdit() {
        String mItemName = mEditTextForList.getText().toString();

        if (!mItemName.equals("")) {

            Firebase firebaseRef = new Firebase(Constants.UNIQUE_FIREBASE_URL);
            Firebase itemsRef = new Firebase(Constants.LIST_ITEMS_LOCATION_URL).child(mListId);

            HashMap<String, Object> updatedItemToAddMap = new HashMap<String, Object>();

            Firebase newRef = itemsRef.push();
            String itemId = newRef.getKey();

            ListItem itemToAddObject = new ListItem(mItemName);
            HashMap<String, Object> itemToAdd =
                    (HashMap<String, Object>) new ObjectMapper().convertValue(itemToAddObject, Map.class);

            updatedItemToAddMap.put("/" + Constants.LIST_ITEMS_LOCATION + "/"
                    + mListId + "/" + itemId, itemToAdd);

            HashMap<String, Object> changedTimestampMap = new HashMap<>();
            changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            updatedItemToAddMap.put("/" + Constants.ACTIVE_LISTS_LOCATION +
                    "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

            firebaseRef.updateChildren(updatedItemToAddMap);


            AddListItemDialogFragment.this.getDialog().cancel();
        }
    }
}
