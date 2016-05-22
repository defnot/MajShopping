package com.dany.majesticshopping.ui.activeListDetails;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.ui.BaseActivity;
import com.dany.majesticshopping.utils.Constants;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Dany on 5/19/2016.
 */
public class ActiveListDetails extends BaseActivity {
    private ListView mListView;
    private MajesticShoppingList mShoppingList;
    private Firebase mDatabaseRef;
    private String mListId;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_active_list_details);

        Intent intent = this.getIntent();
        mListId = intent.getStringExtra(Constants.KEY_LIST_ID);
        if(mListId == null) {
            finish();
            return;
        }
        mDatabaseRef = new Firebase(Constants.ACTIVE_LISTS_LOCATION).child(mListId);

        initializeScreen();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MajesticShoppingList shoppingList = dataSnapshot.getValue(MajesticShoppingList.class);

                if (shoppingList == null) {
                    finish();
                    return;
                }
                mShoppingList = shoppingList;
                invalidateOptionsMenu();
                setTitle(shoppingList.getListName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("op mop", getString(R.string.log_error_the_read_failed) + firebaseError.getMessage());
            }
        });

        invalidateOptionsMenu();

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if(view.getId() != R.id.list_view_footer_empty) {
                    showEditListItemNameDialog();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list_details, menu);


        MenuItem remove = menu.findItem(R.id.action_remove_list);
        MenuItem edit = menu.findItem(R.id.action_edit_list_name);
        MenuItem share = menu.findItem(R.id.action_share_list);
        MenuItem archive = menu.findItem(R.id.action_archive);

         /* Only the edit and remove options are implemented */
        remove.setVisible(true);
        edit.setVisible(true);
        share.setVisible(false);
        archive.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit_list_name) {
            showEditListNameDialog();
            return true;
        }


        if (id == R.id.action_remove_list) {
            removeList();
            return true;
        }


        if (id == R.id.action_share_list) {
            return true;
        }


        if (id == R.id.action_archive) {
            archiveList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_shopping_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
         /* Common toolbar setup */
        setSupportActionBar(toolbar);
         /* Add back button to the action bar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
         /* Inflate the footer, set root layout to null*/
        View footer = getLayoutInflater().inflate(R.layout.footer_empty, null);
        mListView.addFooterView(footer);
    }


    /**
     * Archive current list when user selects "Archive" menu item
     */
    public void archiveList() {
    }


    /**
     * Start AddItemsFromMealActivity to add meal ingredients into the shopping list
     * when the user taps on "add meal" fab
     */
    public void addMeal(View view) {
    }

    /**
     * Remove current shopping list and its items from all nodes
     */
    public void removeList() {
         /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = RemoveListDialogFragment.newInstance(mShoppingList);
        dialog.show(getFragmentManager(), "RemoveListDialogFragment");
    }

    /**
     * Show the add list item dialog when user taps "Add list item" fab
     */
    public void showAddListItemDialog(View view) {
         /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = AddListItemDialogFragment.newInstance(mShoppingList,mListId);
        dialog.show(getFragmentManager(), "AddListItemDialogFragment");
    }

    /**
     * Show edit list name dialog when user selects "Edit list name" menu item
     */
    public void showEditListNameDialog() {
         /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = EditListNameDialogFragment.newInstance(mShoppingList, mListId);
        dialog.show(this.getFragmentManager(), "EditListNameDialogFragment");
    }

    /**
     * Show the edit list item name dialog after longClick on the particular item
     */
    public void showEditListItemNameDialog() {
         /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = EditListItemNameDialogFragment.newInstance(mShoppingList, mListId);
        dialog.show(this.getFragmentManager(), "EditListItemNameDialogFragment");
    }

    /**
     * This method is called when user taps "Start/Stop shopping" button
     */
    public void toggleShopping(View view) {

    }

}

