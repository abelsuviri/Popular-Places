package com.abelsuviri.viewmodel;

import com.abelsuviri.data.model.ItemsModel;
import com.abelsuviri.data.model.ResponseModel;
import com.abelsuviri.data.service.PlacesService;
import com.abelsuviri.viewmodel.mock.MockResponse;
import com.abelsuviri.viewmodel.rule.RxSchedulerRule;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import retrofit2.HttpException;

/**
 * @author Abel Suviri
 */

public class MainViewModelTest {

    @Rule
    public final RxSchedulerRule rxSchedulerRule = new RxSchedulerRule();

    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private PlacesService placesService;

    @Mock
    private Observer<List<ItemsModel>> observer;

    @Mock
    private MainViewModel mainViewModel;

    private ResponseModel response;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mainViewModel = new MainViewModel(placesService);
        Gson gson = new Gson();
        response = gson.fromJson(MockResponse.mockJson, ResponseModel.class);
    }

    @Test
    public void test_get_places_successfully() {
        Mockito.when(placesService.getPlaces("Malaga", "sights", BuildConfig.APP_ID, BuildConfig.APP_KEY, BuildConfig.API_VERSION))
                .thenReturn(Observable.just(response));
        mainViewModel.getPopularPlaces("Malaga", "sights").observeForever(observer);

        List<ItemsModel> placesList = mainViewModel.getPopularPlaces("Malaga", "sights").getValue();

        Assert.assertEquals(mainViewModel.getIsFailed().getValue(), false);
        Assert.assertEquals(placesList, response.response.groups.get(0).items);
        Assert.assertEquals(mainViewModel.cityName(), response.response.location);
    }

    @Test
    public void test_get_places_unsuccessfully() {
        Mockito.when(placesService.getPlaces("Malaga", "sights", BuildConfig.APP_ID, BuildConfig.APP_KEY, BuildConfig.API_VERSION))
                .thenReturn(Observable.error(new Throwable()));
        mainViewModel.getPopularPlaces("Malaga", "sights").observeForever(observer);

        List<ItemsModel> placesList = mainViewModel.getPopularPlaces("Malaga", "sights").getValue();

        Assert.assertEquals(mainViewModel.getIsFailed().getValue(), true);
        Assert.assertNotEquals(placesList, response.response.groups.get(0).items);
        Assert.assertNotEquals(mainViewModel.cityName(), response.response.location);
    }

    @Test
    public void test_get_non_existing_place() {
        Mockito.when(placesService.getPlaces("Malaga", "sights", BuildConfig.APP_ID, BuildConfig.APP_KEY, BuildConfig.API_VERSION))
                .thenReturn(Observable.error(new HttpException(MockResponse.mockNonExistingPlace)));
        mainViewModel.getPopularPlaces("Malaga", "sights").observeForever(observer);

        Assert.assertEquals(mainViewModel.doesPlaceExists().getValue(), false);
    }
}
