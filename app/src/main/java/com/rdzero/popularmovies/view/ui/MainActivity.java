package com.rdzero.popularmovies.view.ui;


import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rdzero.popularmovies.R;
import com.rdzero.popularmovies.service.model.Movies;
import com.rdzero.popularmovies.service.repository.ProjectRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProjectRepository projectRepository;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.test);
        projectRepository = ProjectRepository.getInstance();
        LiveData<List<Movies>> listTest = projectRepository.getMoviesList("top_rated");
        textView.setText();
    }
}
