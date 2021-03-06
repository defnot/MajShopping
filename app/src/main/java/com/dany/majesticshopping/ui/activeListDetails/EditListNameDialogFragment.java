package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by Dany on 5/19/2016.
 */
public class EditListNameDialogFragment extends EditListDialogFragment {
    private static final String LOG_TAG = ActiveListDetails.class.getSimpleName();
    String mListName;

    public static EditListItemNameDialogFragment newInstance(MajesticShoppingList shoppingList, String listId, String encodedEmail) {
        EditListItemNameDialogFragment editListItemNameDialogFragment = new EditListItemNameDialogFragment();

        Bundle bundle = EditListDialogFragment.newInstanceHelper(shoppingList,
                R.layout.dialog_edit_item, listId, encodedEmail);
        editListItemNameDialogFragment.setArguments(bundle);

        return editListItemNameDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListName = getArguments().getString(Constants.KEY_LIST_NAME);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);
        helpSetDefaultValueEditText(mListName);
        return dialog;
    }

    @Override
    protected void doListEdit() {
        final String inputList = mEditTextForList.getText().toString();
        Log.e("editgo","we go in da edit");
        if (!inputList.equals("")) {

                if (mListName != null && mListId != null) {
                    if (!inputList.equals(mListName)) {
                        Log.e("ifgo","we go in da if");
                        Firebase ref = new Firebase(Constants.ACTIVE_LISTS_LOCATION_RENAME_URL).child(mListId);

                        HashMap<String, Object> updatedProperty = new HashMap<>();
                        updatedProperty.put(Constants.FIREBASE_PROPERTY_LIST_NAME, inputList);

                        HashMap<String, Object> changedTimeStamp = new HashMap<>();
                        changedTimeStamp.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                        updatedProperty.put(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimeStamp);

                        ref.updateChildren(updatedProperty);
                }
            }
        }
    }
}

