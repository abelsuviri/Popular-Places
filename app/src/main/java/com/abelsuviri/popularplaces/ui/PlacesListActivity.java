package com.abelsuviri.popularplaces.ui;

import android.content.Intent;
import android.os.Bundle;

import com.abelsuviri.data.model.ItemsModel;
import com.abelsuviri.data.model.VenuesModel;
import com.abelsuviri.popularplaces.R;
import com.abelsuviri.popularplaces.ui.adapter.PlacesAdapter;
import com.abelsuviri.popularplaces.ui.adapter.viewholder.OnPlaceClick;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Abel Suviri
 */

public class PlacesListActivity extends AppCompatActivity implements OnPlaceClick {

    public static final String PLACES_LIST = "PlacesList";
    public static final String CITY_NAME = "CityName";

    private List<ItemsModel> placesListModel;

    @BindView(R.id.placesList)
    RecyclerView placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getIntent().getStringExtra(CITY_NAME));

        Type listType = new TypeToken<List<ItemsModel>>() {}.getType();
        placesListModel = new Gson().fromJson(getIntent().getStringExtra(PLACES_LIST), listType);

        setupList();
    }

    private void setupList() {
        placesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        placesList.addItemDecoration(new DividerItemDecoration(placesList.getContext(), LinearLayoutManager.VERTICAL));
        placesList.setAdapter(new PlacesAdapter(placesListModel, this));
    }

    @Override
    public void onPlaceClick(VenuesModel venue) {
        Intent intent = new Intent(this, PlaceDescriptionActivity.class);
        intent.putExtra(PlaceDescriptionActivity.VENUE, new Gson().toJson(venue));
        startActivity(intent);
    }
}
