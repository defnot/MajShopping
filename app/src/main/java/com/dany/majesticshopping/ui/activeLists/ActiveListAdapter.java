package com.dany.majesticshopping.ui.activeLists;

import com.dany.majesticshopping.R;
import com.dany.majesticshopping.model.MajesticShoppingList;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

/**
 * Created by Dany on 5/21/2016.
 */
public class ActiveListAdapter extends FirebaseListAdapter<MajesticShoppingList> {

    public ActiveListAdapter(Activity activity, Class<MajesticShoppingList> modelClass, int modelLayout,
                             Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, MajesticShoppingList list) {
        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
        TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);


        textViewListName.setText(list.getListName());
        textViewCreatedByUser.setText(list.getOwner());
    }
}
