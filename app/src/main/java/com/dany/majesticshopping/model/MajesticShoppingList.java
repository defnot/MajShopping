package com.dany.majesticshopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ServerValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import com.dany.majesticshopping.utils.Constants;
/**
 * Created by Dany on 5/18/2016.
 */
public class MajesticShoppingList {
    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;
    private HashMap<String, User> usersShopping;

    public MajesticShoppingList() {
    }

    public MajesticShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
        HashMap<String, Object> timestampLastChangedObj = new HashMap<String, Object>();
        timestampLastChangedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;
        this.usersShopping = new HashMap<>();
    }

    public MajesticShoppingList(String listName, String owner, HashMap<String, Object> timestampCreated) {
        this.listName = listName;
        this.owner = owner;
        HashMap<String, Object> timestampLastChangedObj = new HashMap<String, Object>();
        timestampLastChangedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;

        this.timestampCreated = timestampCreated;
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }
    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }


    @JsonIgnore
    public long getTimestampLastChangedLong() {

        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    @JsonIgnore
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    public HashMap getUsersShopping() {
        return usersShopping;
    }
}
