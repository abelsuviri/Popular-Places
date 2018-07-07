package com.abelsuviri.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Abel Suviri
 */

public class IconModel {
    @SerializedName("prefix")
    public String url;

    @SerializedName("suffix")
    public String extension;
}
