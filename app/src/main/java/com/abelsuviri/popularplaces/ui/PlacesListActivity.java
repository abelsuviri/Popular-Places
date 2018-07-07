package com.abelsuviri.popularplaces.ui;

import android.os.Bundle;

import com.abelsuviri.data.model.ItemsModel;
import com.abelsuviri.popularplaces.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Abel Suviri
 */

public class PlacesListActivity extends AppCompatActivity {

    public static final String PLACES_LIST = "PlacesList";

    private List<ItemsModel> placesListModel;

    @BindView(R.id.placesList)
    RecyclerView placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        ButterKnife.bind(this);

        Type listType = new TypeToken<List<ItemsModel>>() {}.getType();
        placesListModel = new Gson().fromJson(getIntent().getStringExtra(PLACES_LIST), listType);

        setupList();
    }

    private void setupList() {

    }
}
