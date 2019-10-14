package com.an.pasifmovies.ui.search.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.an.pasifmovies.data.Resource;
import com.an.pasifmovies.data.local.dao.MovieDao;
import com.an.pasifmovies.data.local.entity.MovieEntity;
import com.an.pasifmovies.data.remote.api.MovieApiService;
import com.an.pasifmovies.data.repository.MovieRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MovieSearchViewModel extends ViewModel {

    @Inject
    public MovieSearchViewModel(MovieDao movieDao, MovieApiService movieApiService) {
        movieRepository = new MovieRepository(movieDao, movieApiService);
    }

    private MovieRepository movieRepository;

    private MutableLiveData<Resource<List<MovieEntity>>> moviesLiveData = new MutableLiveData<>();

    public void searchMovie(String text) {
        movieRepository.searchMovies(1l, text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resource -> getMoviesLiveData().postValue(resource));
    }

    public MutableLiveData<Resource<List<MovieEntity>>> getMoviesLiveData() {
        return moviesLiveData;
    }
}
