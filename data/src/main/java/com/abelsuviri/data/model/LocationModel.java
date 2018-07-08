package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Abel Suviri
 */

public class LocationModel {
    @SerializedName("address")
    public String address;

    @SerializedName("postalCode")
    public String postCode;
}
