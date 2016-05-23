package com.dany.majesticshopping.model;

/**
 * Created by Dany on 5/23/2016.
 */
public class ListItem {
    private String name;
    private String owner;

    public ListItem(String name) {
        this.name = name;
        this.owner = "Anonymous owner";
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

}
