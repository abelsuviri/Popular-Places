package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Abel Suviri
 */

public class ResponseObjectModel {
    @SerializedName("headerLocation")
    public String location;

    @SerializedName("groups")
    public List<GroupsModel> groups;
}
