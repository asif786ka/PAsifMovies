package com.an.pasifmovies.ui.detail.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.an.pasifmovies.AppController;
import com.an.pasifmovies.data.local.dao.MovieDao;
import com.an.pasifmovies.data.local.entity.MovieEntity;
import com.an.pasifmovies.data.remote.api.MovieApiService;
import com.an.pasifmovies.data.repository.MovieRepository;
import com.an.pasifmovies.ui.base.BaseViewModel;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends ViewModel {

    @Inject
    public MovieDetailViewModel(MovieDao movieDao, MovieApiService movieApiService) {
        movieRepository = new MovieRepository(movieDao, movieApiService);
    }

    private MovieRepository movieRepository;

    private MutableLiveData<MovieEntity> movieDetailsLiveData = new MutableLiveData<>();

    public void fetchMovieDetail(MovieEntity movieEntity) {
        movieRepository.fetchMovieDetails(movieEntity.getId())
        .subscribe(resource -> {
            if(resource.isLoaded()) getMovieDetailsLiveData().postValue(resource.data);
        });
    }

    public MutableLiveData<MovieEntity> getMovieDetailsLiveData() {
        return movieDetailsLiveData;
    }
}
