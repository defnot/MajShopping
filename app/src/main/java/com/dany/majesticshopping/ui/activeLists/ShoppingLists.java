package com.dany.majesticshopping.ui.activeLists;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;
import com.dany.majesticshopping.ui.activeListDetails.ActiveListDetails;
import com.dany.majesticshopping.utils.Constants;
import com.firebase.client.Firebase;


/**
 * Created by Dany on 5/17/2016.
 */
public class ShoppingLists extends Fragment {
    private String mEncodedEmail;
    private ListView mListView;
    private ActiveListAdapter mListsAdapter;
    public ShoppingLists() {
        /* Required empty public constructor */
    }

    public static ShoppingLists newInstance(String encodedEmail) {
        ShoppingLists fragment = new ShoppingLists();
        Bundle args = new Bundle();
        args.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        fragment.setArguments(args);
        return fragment;
    }
    

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);

        Firebase listsReference = new Firebase(Constants.ACTIVE_LISTS_LOCATION_RENAME_URL);

        mListsAdapter = new ActiveListAdapter(getActivity(), MajesticShoppingList.class,
                R.layout.single_active_list, listsReference, mEncodedEmail);

        mListView.setAdapter(mListsAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MajesticShoppingList selectedList = mListsAdapter.getItem(position);
                if(selectedList != null) {
                    Intent intent = new Intent(getActivity(), ActiveListDetails.class);
                    String listId = mListsAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
    }
}
