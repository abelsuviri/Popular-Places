package com.abelsuviri.data.service;

import com.abelsuviri.data.model.ResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Abel Suviri
 */

public interface PlacesService {
    @GET("v2/venues/explore")
    Observable<ResponseModel> getPlaces(
            @Query("near") String location,
            @Query("section") String placeType,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientKey,
            @Query("v") String version
    );
}
