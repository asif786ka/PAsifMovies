package com.an.pasifmovies.di.module;

import com.an.pasifmovies.ui.detail.activity.MovieDetailActivity;
import com.an.pasifmovies.ui.detail.activity.TvDetailActivity;
import com.an.pasifmovies.ui.main.activity.MainActivity;
import com.an.pasifmovies.ui.search.activity.MovieSearchActivity;
import com.an.pasifmovies.ui.search.activity.TvSearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract MovieDetailActivity contributeMovieDetailActivity();

    @ContributesAndroidInjector()
    abstract TvDetailActivity contributeTvDetailActivity();

    @ContributesAndroidInjector()
    abstract MovieSearchActivity contributeMovieSearchActivity();

    @ContributesAndroidInjector()
    abstract TvSearchActivity contributeTvSearchActivity();
}