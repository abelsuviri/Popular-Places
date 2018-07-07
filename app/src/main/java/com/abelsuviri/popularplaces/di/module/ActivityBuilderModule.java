package com.abelsuviri.popularplaces.di.module;

import com.abelsuviri.popularplaces.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Abel Suviri
 */

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
