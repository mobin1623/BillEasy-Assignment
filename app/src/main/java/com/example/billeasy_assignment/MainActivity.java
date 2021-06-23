package com.example.billeasy_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.billeasy_assignment.Adapter.MovieAdapter;
import com.example.billeasy_assignment.DB.MovieDatabase;
import com.example.billeasy_assignment.DB.Movies;
import com.example.billeasy_assignment.Utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieContract.View {

    private MoviePresenter moviePresenter;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movies> list , localList;
    private MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDatabase = MovieDatabase.getInstance(getApplicationContext());

        list = new ArrayList<>();
        localList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);

        moviePresenter = new MoviePresenter(this);

        if (AppUtil.isNetworkAvailable(getApplicationContext())){
            moviePresenter.requestDataFromServer();
        }else {
            localList = movieDatabase.getMovieDao().getMovies();

            if (localList.size() > 0){
                list.addAll(localList);
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getApplicationContext(), "No Local data found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setDataInRecyclerView(List<Movies> moviesList) {
        Log.d("TAG","Recyclerview : " + moviesList.toString());
        list.addAll(moviesList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void saveDataInDB(Movies movies) {
        movieDatabase.getMovieDao().insertMovies(movies);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("TAG","Error : " + throwable.getMessage());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.onDestroy();
    }
}