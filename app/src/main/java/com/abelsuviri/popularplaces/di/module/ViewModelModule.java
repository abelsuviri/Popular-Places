package com.abelsuviri.popularplaces.di.module;

import com.abelsuviri.popularplaces.di.scope.ViewModelScope;
import com.abelsuviri.viewmodel.MainViewModel;
import com.abelsuviri.viewmodel.factory.ViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Abel Suviri
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelScope(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
