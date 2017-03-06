package com.hiczp.minecraft.onlinewhitelist.entity;

/**
 * Created by czp on 17-3-6.
 */
public class OnlineWhiteListEntity {
    private Integer id;
    private String username;
    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
