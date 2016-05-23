package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by Dany on 5/19/2016.
 */
public class EditListItemNameDialogFragment extends EditListDialogFragment {
    String mItemName, mItemId;

    public static EditListItemNameDialogFragment newInstance(MajesticShoppingList shoppingList, String itemName,
                                                             String itemId, String listId) {
        EditListItemNameDialogFragment editListItemNameDialogFragment = new EditListItemNameDialogFragment();

        Bundle bundle = EditListDialogFragment.newInstanceHelper(shoppingList, R.layout.dialog_edit_item, listId);
        bundle.putString(Constants.KEY_LIST_ITEM_NAME, itemName);
        bundle.putString(Constants.KEY_LIST_ITEM_ID, itemId);
        editListItemNameDialogFragment.setArguments(bundle);

        return editListItemNameDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemName = getArguments().getString(Constants.KEY_LIST_ITEM_NAME);
        mItemId = getArguments().getString(Constants.KEY_LIST_ITEM_ID);
    }


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);

        super.helpSetDefaultValueEditText(mItemName);

        return dialog;
    }

    protected void doListEdit() {
        String nameInput = mEditTextForList.getText().toString();

        if (!nameInput.equals("") && !nameInput.equals(mItemName)) {
            Firebase firebaseRef = new Firebase(Constants.UNIQUE_FIREBASE_URL);

            HashMap<String, Object> updatedItemToAddMap = new HashMap<String, Object>();

            updatedItemToAddMap.put("/" + Constants.LIST_ITEMS_LOCATION + "/"
                            + mListId + "/" + mItemId + "/" + Constants.FIREBASE_PROPERTY_ITEM_NAME,
                    nameInput);

            HashMap<String, Object> changedTimestampMap = new HashMap<>();
            changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            updatedItemToAddMap.put("/" + Constants.ACTIVE_LISTS_LOCATION +
                    "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

            firebaseRef.updateChildren(updatedItemToAddMap);

        }
    }
}
