package com.example.billeasy_assignment;

import android.util.Log;

import com.example.billeasy_assignment.DB.Movies;

import java.util.List;

public class MoviePresenter implements MovieContract.Presenter,
        MovieContract.Model.OnFinishListerner {
    private MovieContract.View movieView;
    private MovieContract.Model movieModel;

    public MoviePresenter(MovieContract.View movieView) {
        this.movieView = movieView;
        movieModel = new MovieListModel();
    }

    @Override
    public void onDestroy() {
        this.movieView = null;

    }

    @Override
    public void requestDataFromServer() {
        movieModel.getMovies(this);

    }

    @Override
    public void onFinish(List<Movies> moviesList) {
        Log.d("TAG","Onfinish : " + moviesList.size());
        movieView.setDataInRecyclerView(moviesList);


    }

    @Override
    public void onFinish(Movies movies) {
        movieView.saveDataInDB(movies);
    }

    @Override
    public void onFailure(Throwable t) {
        movieView.onResponseFailure(t);

    }
}
