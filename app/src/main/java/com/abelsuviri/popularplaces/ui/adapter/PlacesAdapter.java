package com.abelsuviri.popularplaces.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abelsuviri.data.model.ItemsModel;
import com.abelsuviri.data.model.VenuesModel;
import com.abelsuviri.popularplaces.R;
import com.abelsuviri.popularplaces.ui.adapter.viewholder.OnPlaceClick;
import com.abelsuviri.popularplaces.ui.adapter.viewholder.PlacesViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Abel Suviri
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesViewHolder> {
    private List<ItemsModel> venuesList;

    private OnPlaceClick onPlaceClick;

    public PlacesAdapter(List<ItemsModel> venuesList, OnPlaceClick onPlaceClick) {
        this.venuesList = venuesList;
        this.onPlaceClick = onPlaceClick;
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new PlacesViewHolder(itemView, onPlaceClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder holder, int position) {
        VenuesModel venuesModel = venuesList.get(position).venue;
        holder.bindViews(venuesModel);
    }

    @Override
    public int getItemCount() {
        return venuesList.size();
    }
}
