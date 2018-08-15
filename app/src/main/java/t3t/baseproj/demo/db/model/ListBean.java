package t3t.baseproj.demo.db.model;

import com.google.gson.Gson;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dale on 2018/8/15.
 */
public class ListBean extends RealmObject {
    /**
     * name : Barcelona
     * votes : 23
     */
    @PrimaryKey
    private String name;
    private int votes;

    public static ListBean objectFromData(String str) {

        return new Gson().fromJson(str, ListBean.class);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
