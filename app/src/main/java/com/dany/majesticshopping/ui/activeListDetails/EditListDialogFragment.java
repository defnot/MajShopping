package com.dany.majesticshopping.ui.activeListDetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
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

/**
 * Created by Dany on 5/19/2016.
 */
public abstract class EditListDialogFragment extends DialogFragment {
    String mListId;
    EditText mEditTextForList;
    int mResource;

    protected static Bundle newInstanceHelper(MajesticShoppingList shoppingList, int resource, String listId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_LIST_ID,listId);
        bundle.putInt(Constants.KEY_LAYOUT_RESOURCE, resource);
        return bundle;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListId = getArguments().getString(Constants.KEY_LIST_ID);
        mResource = getArguments().getInt(Constants.KEY_LAYOUT_RESOURCE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    protected Dialog createDialogHelper(int stringResourceForPositiveButton) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(mResource, null);
        mEditTextForList = (EditText) rootView.findViewById(R.id.edit_text_list_dialog);

        mEditTextForList.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    doListEdit();

                    EditListDialogFragment.this.getDialog().cancel();
                }
                return true;
            }
        });

        builder.setView(rootView)

                .setPositiveButton(stringResourceForPositiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        doListEdit();

                        EditListDialogFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton(R.string.negative_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        EditListDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    protected void helpSetDefaultValueEditText(String defaultText) {
        if(defaultText != null) {
            mEditTextForList.setText(defaultText);
            mEditTextForList.setSelection(defaultText.length());
        }
    }


    protected abstract void doListEdit();
}
