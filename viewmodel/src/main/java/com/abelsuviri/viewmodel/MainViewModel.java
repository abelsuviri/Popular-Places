package com.abelsuviri.viewmodel;

import android.annotation.SuppressLint;

import com.abelsuviri.data.model.ItemsModel;
import com.abelsuviri.data.model.ResponseObjectModel;
import com.abelsuviri.data.service.PlacesService;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Abel Suviri
 */

public class MainViewModel extends ViewModel {
    private PlacesService placesService;
    private MutableLiveData<String> cityName = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFailed = new MutableLiveData<>();

    @Inject
    MainViewModel(PlacesService placesService) {
        this.placesService = placesService;
        isLoading.setValue(false);
    }

    /**
     * This method makes the call to the server to retrieve the popular places.
     * @return popular places list
     */
    @SuppressLint("CheckResult")
    public LiveData<List<ItemsModel>> getPopularPlaces(String location, String placesType) {
        MutableLiveData<List<ItemsModel>> placesList = new MutableLiveData<>();
        isLoading.setValue(true);
        isFailed.setValue(false);
        placesService.getPlaces(location, placesType, BuildConfig.APP_ID, BuildConfig.APP_KEY, BuildConfig.API_VERSION)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    isLoading.setValue(false);
                    cityName.setValue(response.response.location);
                    placesList.setValue(response.response.groups.get(0).items);
                }, error -> {
                    isFailed.setValue(true);
                    isLoading.setValue(false);
                });

        return placesList;
    }

    /**
     * This method will trigger the loading layer in the view
     * @return boolean loading
     */
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * This method will trigger the retry dialog in the view
     * @return boolean isFailed
     */
    public MutableLiveData<Boolean> getIsFailed() {
        return isFailed;
    }
}
