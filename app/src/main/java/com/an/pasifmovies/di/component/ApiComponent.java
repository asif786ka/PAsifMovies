package com.an.pasifmovies.di.component;


import android.app.Application;

import com.an.pasifmovies.AppController;
import com.an.pasifmovies.di.module.ActivityModule;
import com.an.pasifmovies.di.module.ApiModule;
import com.an.pasifmovies.di.module.DbModule;
import com.an.pasifmovies.di.module.FragmentModule;
import com.an.pasifmovies.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApiModule.class, DbModule.class, ViewModelModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class})
public interface ApiComponent {

//    void inject(MovieListViewModel moviesListViewModel);
//    void inject(MovieDetailViewModel moviesDetailViewModel);
//    void inject(MovieSearchViewModel movieSearchViewModel);
//
//
//    void inject(TvListViewModel moviesListViewModel);
//    void inject(TvDetailViewModel tvDetailViewModel);
//    void inject(TvSearchViewModel tvSearchViewModel);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        @BindsInstance
        Builder dbModule(DbModule dbModule);

        ApiComponent build();
    }

    void inject(AppController appController);
}
