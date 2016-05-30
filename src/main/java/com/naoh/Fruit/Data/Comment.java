package com.naoh.Fruit.Data;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Comment {
    int id;
    int rank;
    String content;

    public Comment(int id, int rank, String content) {
        this.id = id;
        this.rank = rank;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
