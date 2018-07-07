package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Abel Suviri
 */

public class GroupsModel {
    @SerializedName("items")
    public List<ItemsModel> items;
}
