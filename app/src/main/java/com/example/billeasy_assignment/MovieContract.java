package com.example.billeasy_assignment;

import com.example.billeasy_assignment.DB.Movies;

import java.util.List;

public interface MovieContract {

    interface Model{
        interface OnFinishListerner{
            void onFinish(List<Movies> moviesList);

            void onFailure(Throwable t);
        }

        void getMovies(OnFinishListerner listerner);
    }

    interface View {
        void setDataInRecyclerView(List<Movies> moviesList);
        void saveDataInDB(Movies movies);

        void onResponseFailure(Throwable throwable);


    }

    interface Presenter {

        void onDestroy();
        void requestDataFromServer();

    }
}
