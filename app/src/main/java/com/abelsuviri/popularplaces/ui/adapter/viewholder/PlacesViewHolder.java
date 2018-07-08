package com.abelsuviri.popularplaces.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abelsuviri.data.model.CategoriesModel;
import com.abelsuviri.data.model.VenuesModel;
import com.abelsuviri.popularplaces.R;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Abel Suviri
 */

public class PlacesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.venueName)
    TextView venueName;

    @BindView(R.id.category)
    TextView venueCategory;

    @BindView(R.id.venueIcon)
    ImageView venueIcon;

    private VenuesModel venue;
    private OnPlaceClick onPlaceClick;

    public PlacesViewHolder(View itemView, OnPlaceClick onPlaceClick) {
        super(itemView);
        this.onPlaceClick = onPlaceClick;

        ButterKnife.bind(this, itemView);
    }

    public void bindViews(VenuesModel venue) {
        this.venue = venue;
        venueName.setText(venue.name);

        CategoriesModel category = venue.categories.get(0);

        venueCategory.setText(category.name);

        Picasso.with(itemView.getContext())
                .load(category.icon.url + 64 + category.icon.extension)
                .into(venueIcon);
    }

    @OnClick(R.id.itemLayout)
    public void setOnPlaceClick() {
        onPlaceClick.onPlaceClick(venue);
    }
}
