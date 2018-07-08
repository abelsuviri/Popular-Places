package com.abelsuviri.popularplaces.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.abelsuviri.data.model.VenuesModel;
import com.abelsuviri.popularplaces.R;
import com.google.gson.Gson;

/**
 * @author Abel Suviri
 */

public class PlaceDescriptionActivity extends AppCompatActivity {

    public static final String VENUE = "Venue";

    @BindView(R.id.locationName)
    TextView locationName;

    @BindView(R.id.addressTitle)
    TextView addressTitle;

    @BindView(R.id.address)
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_description);

        ButterKnife.bind(this);

        fillViews();
    }

    private void fillViews() {
        VenuesModel venue = new Gson().fromJson(getIntent().getStringExtra(VENUE), VenuesModel.class);

        locationName.setText(venue.name);

        String fullAddress = null;

        if (venue.location != null && venue.location.address != null) {
            fullAddress = (venue.location.postCode != null) ?
                    venue.location.address.concat(", ").concat(venue.location.postCode) : venue.location.address;
        }

        if (fullAddress != null) {
            address.setText(fullAddress);
        } else {
            addressTitle.setVisibility(View.INVISIBLE);
        }
    }
}
