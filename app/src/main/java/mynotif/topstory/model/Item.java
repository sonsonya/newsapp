package mynotif.topstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    public String Id;
    public String Title;
    public String Descendants;
    public String Score;

    public Item(String id, String title, String descendants, String score) {
        Id = id;
        Title=title;
        Descendants=descendants;
        Score=score;

    }
}