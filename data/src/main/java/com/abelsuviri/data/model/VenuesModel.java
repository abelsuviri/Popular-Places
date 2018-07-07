package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Abel Suviri
 */

public class VenuesModel {
    @SerializedName("name")
    public String name;

    @SerializedName("location")
    public LocationModel location;

    @SerializedName("categories")
    public List<CategoriesModel> categories;
}
