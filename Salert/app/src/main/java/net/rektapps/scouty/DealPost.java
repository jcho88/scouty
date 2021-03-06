package net.rektapps.scouty;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Justin on 5/23/2016.
 */

@ParseClassName("DealPost")
public class DealPost extends ParseObject {

    public String getText() {
        return getString("deal_info");
    }

    public void setText(String value) {
        put("deal_info", value);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }

    public String getStoreId(){
        return getString("store_id");
    }

    public void setStoreId(String value){
        put("store_id", value);
    }

    public static ParseQuery<DealPost> getQuery() {
        return ParseQuery.getQuery(DealPost.class);
    }
}
