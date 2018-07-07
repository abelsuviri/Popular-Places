package com.abelsuviri.popularplaces.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.abelsuviri.popularplaces.R;
import com.abelsuviri.viewmodel.MainViewModel;
import com.google.gson.Gson;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * @author Abel Suviri
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cityName)
    TextView cityName;

    @BindView(R.id.placeCategory)
    Spinner placeCategory;

    @BindView(R.id.loadingLayout)
    RelativeLayout loadingLayout;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        initSubscriptions();
    }

    /**
     * This method will observe for changes in the isLoading and isFailed variables.
     * If isLoading is true then the loading layer will be visible, if false it will be hidden.
     * If isFailed is true then a retry dialog will be visible
     */
    private void initSubscriptions() {
       mainViewModel.getIsLoading().observe(this, isLoading -> loadingLayout.setVisibility((isLoading) ? View.VISIBLE : View.GONE));

        mainViewModel.getIsFailed().observe(this, isFailed -> {
            if (isFailed) {
                showRetryDialog();
            }
        });
    }

    /**
     * This method displays an AlertDialog to try again to call the server.
     */
    private void showRetryDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getResources().getString(R.string.request_error));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.retry), (dialog, i) -> {
            dialog.dismiss();
            onClick();
        }).show();
    }

    @OnClick(R.id.search)
    public void onClick() {
        mainViewModel.getPopularPlaces(cityName.getText().toString(), placeCategory.getSelectedItem().toString()).observe(this, response -> {
            Intent intent = new Intent(this, PlacesListActivity.class);
            intent.putExtra(PlacesListActivity.PLACES_LIST, new Gson().toJson(response));
            startActivity(intent);
        });
    }
}
