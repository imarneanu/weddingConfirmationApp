package com.imarneanu.alexiuliawedding;

import com.imarneanu.alexiuliawedding.adapters.GuestAdapter;
import com.imarneanu.alexiuliawedding.custom.EmptyLayout;
import com.imarneanu.alexiuliawedding.data.DatabaseOperations;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_empty_layout)
    EmptyLayout mEmptyLayout;

    private GuestAdapter mAdapter;

    private ArrayList<GuestModel> mGuestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GuestAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener((() -> {
            refreshContent();
            mSwipeRefreshLayout.setRefreshing(false);
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshContent();
        if (mGuestsList.size() == 0) {
            mEmptyLayout.showEmpty();
            return;
        }
        mEmptyLayout.setVisibility(View.GONE);
    }

    private void refreshContent() {
        mGuestsList = loadGuestFromDatabase();
        mAdapter.setGuests(mGuestsList);
    }

    private ArrayList<GuestModel> loadGuestFromDatabase() {
        return DatabaseOperations.getGuests(this, null, null, null);
    }
}
