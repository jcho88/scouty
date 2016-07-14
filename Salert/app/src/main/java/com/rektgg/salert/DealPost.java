package com.rektgg.salert;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Justin on 5/23/2016.
 */
@ParseClassName("DealPost")
public class DealPost extends ParseObject {

    public DealPost(){
        super();
    }

    public String getText() {
        return getString("text");
    }

    public void setText(String value) {
        put("text", value);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }

    public static ParseQuery<DealPost> getQuery() {
        return ParseQuery.getQuery(DealPost.class);
    }
}
