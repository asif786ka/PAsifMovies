package com.an.pasifmovies.api;

import com.an.pasifmovies.AppConstants;
import com.an.pasifmovies.data.local.entity.TvEntity;
import com.an.pasifmovies.data.remote.api.TvApiService;
import com.an.pasifmovies.data.remote.model.CreditResponse;
import com.an.pasifmovies.data.remote.model.TvApiResponse;
import com.an.pasifmovies.data.remote.model.VideoResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TvApiServiceTest extends ApiAbstract<TvApiService> {

    private TvApiService service;

    @Before
    public void initService() {
        this.service = createService(TvApiService.class);
    }

    @Test
    public void fetchTvDetailsTest() throws IOException {
        enqueueResponse("test_tv_details.json");
        TvEntity tvEntity = service.fetchTvDetail("1").blockingFirst();
        Assert.assertEquals("The Flash", tvEntity.getHeader());
        Assert.assertEquals(String.format(AppConstants.IMAGE_URL, "/fki3kBlwJzFp8QohL43g9ReV455.jpg"), tvEntity.getPosterPath());
    }


    @Test
    public void fetchTvVideosTest() throws IOException {
        enqueueResponse("test_movie_videos.json");
        VideoResponse videoResponse = service.fetchTvVideo("1").blockingFirst();
        Assert.assertEquals(335983, videoResponse.getId());
        Assert.assertEquals(4, videoResponse.getResults().size());
        Assert.assertEquals("dzxFdtWmjto", videoResponse.getResults().get(0).getKey());
    }


    @Test
    public void fetchTvCastsTest() throws IOException {
        enqueueResponse("test_movie_cast.json");
        CreditResponse creditResponse = service.fetchCastDetail("1").blockingFirst();
        Assert.assertEquals(102, creditResponse.getCast().size());
        Assert.assertEquals(48, creditResponse.getCrew().size());
    }


    @Test
    public void fetchTvSimilarTest() throws IOException {
        enqueueResponse("test_tv_similar.json");
        TvApiResponse tvApiResponse = service.fetchSimilarTvList("1", 1).blockingFirst();
        Assert.assertEquals(1, tvApiResponse.getPage());
        Assert.assertEquals(20, tvApiResponse.getResults().size());
    }

    @Test
    public void searchTvsTest() throws IOException {
        enqueueResponse("test_search_tv.json");
        TvApiResponse tvApiResponse = service.searchTvsByQuery("friends", "1").blockingFirst();
        Assert.assertEquals(1, tvApiResponse.getPage());
        Assert.assertEquals(20, tvApiResponse.getResults().size());
        Assert.assertEquals("Friends", tvApiResponse.getResults().get(0).getHeader());
    }
}
