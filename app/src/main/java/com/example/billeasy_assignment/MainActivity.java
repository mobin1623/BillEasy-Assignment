package com.example.billeasy_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.billeasy_assignment.Adapter.MovieAdapter;
import com.example.billeasy_assignment.DB.Movies;
import com.example.billeasy_assignment.Utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieContract.View {

    private MoviePresenter moviePresenter;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movies> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);

        moviePresenter = new MoviePresenter(this);

        if (AppUtil.isNetworkAvailable(getApplicationContext())){
            moviePresenter.requestDataFromServer();
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

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.onDestroy();
    }
}