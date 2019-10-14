package com.an.pasifmovies.ui.search.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.an.pasifmovies.AppController;
import com.an.pasifmovies.data.Resource;
import com.an.pasifmovies.data.local.dao.TvDao;
import com.an.pasifmovies.data.local.entity.TvEntity;
import com.an.pasifmovies.data.remote.api.TvApiService;
import com.an.pasifmovies.data.repository.TvRepository;
import com.an.pasifmovies.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TvSearchViewModel extends ViewModel {

    @Inject
    public TvSearchViewModel(TvDao tvDao, TvApiService tvApiService) {
        tvRepository = new TvRepository(tvDao, tvApiService);
    }

    private TvRepository tvRepository;

    private MutableLiveData<Resource<List<TvEntity>>> tvsLiveData = new MutableLiveData<>();

    public void searchTv(String text) {
        tvRepository.searchTvs(1l, text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resource -> getTvsLiveData().postValue(resource));
    }

    public MutableLiveData<Resource<List<TvEntity>>> getTvsLiveData() {
        return tvsLiveData;
    }
}
