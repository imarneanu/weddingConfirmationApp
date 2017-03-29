package com.imarneanu.alexiuliawedding;

import com.imarneanu.alexiuliawedding.adapters.GuestAdapter;
import com.imarneanu.alexiuliawedding.custom.EmptyLayout;
import com.imarneanu.alexiuliawedding.data.DatabaseOperations;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_empty_layout)
    EmptyLayout mEmptyLayout;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<GuestModel> mGuestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

// TODO uncomment to generate new Firebase token
//        Intent deleteTokenService = new Intent(this, DeleteTokenService.class);
//        startService(deleteTokenService);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mGuestsList = loadGuestFromDatabase();
        if (mGuestsList.size() == 0) {
            mEmptyLayout.showEmpty();
            return;
        }

        mAdapter = new GuestAdapter(mGuestsList);
        mRecyclerView.setAdapter(mAdapter);
        mEmptyLayout.setVisibility(View.GONE);
    }

    private ArrayList<GuestModel> loadGuestFromDatabase() {
        return DatabaseOperations.getGuests(this, null, null, null);
    }
}
