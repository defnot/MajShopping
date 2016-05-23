package com.dany.majesticshopping.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;

/**
 * Created by Dany on 5/19/2016.
 */
public class AddListItemDialogFragment extends EditListDialogFragment {

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static AddListItemDialogFragment newInstance(MajesticShoppingList shoppingList, String listId) {
        AddListItemDialogFragment addListItemDialogFragment = new AddListItemDialogFragment();

        Bundle bundle = newInstanceHelper(shoppingList, R.layout.dialog_add_item,listId);
        addListItemDialogFragment.setArguments(bundle);

        return addListItemDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /** {@link EditListDialogFragment#createDialogHelper(int)} is a
         * superclass method that creates the dialog
         **/
        return super.createDialogHelper(R.string.positive_button_add_list_item);
    }

    /**
     * Adds new item to the current shopping list
     */
    @Override
    protected void doListEdit() {
        //this code should add a new item to the list and also update the timestamp of the list
        //we can make this with one call to the server
    }
}
