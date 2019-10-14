package com.an.pasifmovies.di.module;

import com.an.pasifmovies.ui.main.fragment.MovieListFragment;
import com.an.pasifmovies.ui.main.fragment.TvListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract TvListFragment contributeTvListFragment();
}
