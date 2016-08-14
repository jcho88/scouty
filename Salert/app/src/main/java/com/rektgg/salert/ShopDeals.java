package com.rektgg.salert;

/**
 * Created by steve_000 on 5/19/2016.
 */
public class ShopDeals {

    public String username;
    public String storeID;
    public String dealDetails;

    public ShopDeals(){
        super();
    }

    public ShopDeals(String userName, String shopDeals, String storeID) {
        super();
        this.username = userName;
        this.storeID = storeID;
        this.dealDetails = shopDeals;
    }
}
