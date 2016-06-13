package com.dany.majesticshopping.model;

/**
 * Created by Dany on 5/23/2016.
 */
public class ListItem {
    private String name;
    private String owner;
    private String boughtBy;
    private boolean bought;


    public ListItem() {}

    public ListItem(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.boughtBy = null;
        this.bought = false;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getBoughtBy() {
        return getBoughtBy();
    }

    public boolean isBought() {
        return bought;
    }

}
