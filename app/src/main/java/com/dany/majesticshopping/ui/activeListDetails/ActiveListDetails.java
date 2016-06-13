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
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.utils.Utils;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.dany.majesticshopping.model.ListItem;

import java.util.HashMap;

/**
 * Created by Dany on 5/19/2016.
 */
public class ActiveListDetails extends BaseActivity {
    private ListView mListView;
    private MajesticShoppingList mShoppingList;
    private ListItemAdapter mListItemAdapter;
    private Firebase mListRef;
    private String mListId;
    private boolean mCurrentUserIsOwner = false;
    private ValueEventListener mActiveListRefListener;
    private static final String LOG_TAG = ActiveListDetails.class.getSimpleName();

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
        mListRef = new Firebase(Constants.ACTIVE_LISTS_LOCATION_RENAME_URL).child(mListId);
        Firebase listItemsRef = new Firebase(Constants.LIST_ITEMS_LOCATION_URL).child(mListId);

        initializeScreen();

        mListItemAdapter = new ListItemAdapter(this, ListItem.class,
                R.layout.single_active_list_item, listItemsRef, mListId,mEncodedEmail);
        mListView.setAdapter(mListItemAdapter);

        mActiveListRefListener = mListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MajesticShoppingList shoppingList = dataSnapshot.getValue(MajesticShoppingList.class);

                if (shoppingList == null) {
                    finish();
                    return;
                }
                mShoppingList = shoppingList;
                mListItemAdapter.setShoppingList(mShoppingList);
                mCurrentUserIsOwner = Utils.checkIfOwner(shoppingList, mEncodedEmail); //check if this is the current user

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
                    ListItem listItem = mListItemAdapter.getItem(position);
                    if(listItem != null) {
                        String name = listItem.getName();
                        String itemId = mListItemAdapter.getRef(position).getKey();

                        showEditListItemNameDialog(name, itemId);
                        return true;
                    }
                }
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* Check that the view is not the empty footer item */
                if (view.getId() != R.id.list_view_footer_empty) {
                    final ListItem selectedListItem = mListItemAdapter.getItem(position);
                    String itemId = mListItemAdapter.getRef(position).getKey();

                    if (selectedListItem != null) {

                            /* Create map and fill it in with deep path multi write operations list */
                        HashMap<String, Object> updatedItemBoughtData = new HashMap<String, Object>();

                            /* Buy selected item if it is NOT already bought */
                        if (!selectedListItem.isBought()) {
                            updatedItemBoughtData.put(Constants.FIREBASE_PROPERTY_BOUGHT, true);
                            updatedItemBoughtData.put(Constants.FIREBASE_PROPERTY_BOUGHT_BY, mEncodedEmail);
                        } else {
                            updatedItemBoughtData.put(Constants.FIREBASE_PROPERTY_BOUGHT, false);
                            updatedItemBoughtData.put(Constants.FIREBASE_PROPERTY_BOUGHT_BY, null);
                        }

                            /* Do update */
                        Firebase firebaseItemLocation = new Firebase(Constants.FIREBASE_URL_SHOPPING_LIST_ITEMS)
                                .child(mListId).child(itemId);
                        firebaseItemLocation.updateChildren(updatedItemBoughtData, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                if (firebaseError != null) {
                                    Log.d(LOG_TAG, getString(R.string.log_error_updating_data) +
                                            firebaseError.getMessage());
                                }
                            }
                        });
                    }
                }
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
        remove.setVisible(mCurrentUserIsOwner);
        edit.setVisible(mCurrentUserIsOwner);
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
        mListItemAdapter.cleanup();
        mListRef.removeEventListener(mActiveListRefListener);
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_shopping_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        View footer = getLayoutInflater().inflate(R.layout.footer_empty, null);
        mListView.addFooterView(footer);
    }


    public void archiveList() {
    }

    public void addMeal(View view) {
    }

    public void removeList() {
        DialogFragment dialog = RemoveListDialogFragment.newInstance(mShoppingList, mListId);
        dialog.show(getFragmentManager(), "RemoveListDialogFragment");
    }

    public void showAddListItemDialog(View view) {
        DialogFragment dialog = AddListItemDialogFragment.newInstance(mShoppingList,mListId, mEncodedEmail);
        dialog.show(getFragmentManager(), "AddListItemDialogFragment");
    }

    public void showEditListNameDialog() {
        DialogFragment dialog = EditListNameDialogFragment.newInstance(mShoppingList, mListId,mEncodedEmail);
        dialog.show(this.getFragmentManager(), "EditListNameDialogFragment");
    }

    public void showEditListItemNameDialog(String itemName, String itemId) {
        DialogFragment dialog = EditListItemNameDialogFragment.newInstance(mShoppingList, mListId,itemId,mListId,mEncodedEmail);
        dialog.show(this.getFragmentManager(), "EditListItemNameDialogFragment");
    }

    public void toggleShopping(View view) {

    }

}

