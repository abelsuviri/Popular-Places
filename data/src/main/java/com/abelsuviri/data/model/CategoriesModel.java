package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Abel Suviri
 */

public class CategoriesModel {
    @SerializedName("name")
    public String name;

    @SerializedName("icon")
    public IconModel icon;
}
