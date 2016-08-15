package net.rektapps.scouty;

/**
 * Created by steve_000 on 5/19/2016.
 */
public class ShopDeals {

    public String username;
    public String storeID;
    public String dealDetails;
    public String create_at;

    public ShopDeals(){
        super();
    }

    public ShopDeals(String userName, String shopDeals, String storeID, String create_at) {
        super();
        this.username = userName;
        this.storeID = storeID;
        this.dealDetails = shopDeals;
        this.create_at = create_at;
    }
}
