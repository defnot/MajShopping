package com.dany.majesticshopping.ui.activeLists;

import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAssignedNumbers;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by Dany on 5/17/2016.
 */
public class AddList extends DialogFragment {
    EditText mEditTextListName;

    public static AddList newInstance() {
        AddList addList = new AddList();
        Bundle bundle = new Bundle();
        addList.setArguments(bundle);
        return addList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list, null);
        mEditTextListName = (EditText) rootView.findViewById(R.id.edit_text_list_name);

        /**
         * Call addShoppingList() when user taps "Done" keyboard action
         */
        mEditTextListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addShoppingList();
                }
                return true;
            }
        });

        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addShoppingList();
                    }
                });

        return builder.create();
    }
    // add new list
    public void addShoppingList() {
        String name = mEditTextListName.getText().toString();
        String owner = "Anonymous owner";

        MajesticShoppingList currList = new MajesticShoppingList(name, owner);
        if(!name.equals("")) {
            Firebase listRef = new Firebase(Constants.ACTIVE_LISTS_LOCATION);
            Firebase newListRef = listRef.push();

            final String listId = newListRef.getKey();

            HashMap<String, Object> timestampCreated = new HashMap<>();
            timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

            MajesticShoppingList newShoppingList = new MajesticShoppingList(name, owner, timestampCreated);
            newListRef.setValue(newShoppingList);
            AddList.this.getDialog().cancel();
        }
    }

}

