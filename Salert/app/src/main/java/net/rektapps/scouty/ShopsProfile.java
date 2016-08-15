package net.rektapps.scouty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by steve_000 on 5/23/2016.
 */
public class ShopsProfile implements Serializable{

    public String shop_name;
    public String storeID;
    public String shop_address;
    public ArrayList<ShopDeals> shop_deals;
    public String user_distance;
    //shop image

    public ShopsProfile(){
        super();
    }

    public ShopsProfile(String shopname, ArrayList<ShopDeals> shopdeals, String shopaddress, String userDistance, String storeID) {
        super();
        this.shop_name = shopname;
        this.storeID = storeID;
        this.shop_deals = shopdeals;
        this.shop_address = shopaddress;
        this.user_distance = userDistance;
        //shop image
    }

}
