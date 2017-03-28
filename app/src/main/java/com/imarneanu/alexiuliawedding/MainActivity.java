package com.imarneanu.alexiuliawedding;

import com.imarneanu.alexiuliawedding.custom.EmptyLayout;
import com.imarneanu.alexiuliawedding.data.DatabaseOperations;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mGuestsList = loadGuestFromDatabase();
        if (mGuestsList.size() == 0) {
            mEmptyLayout.showEmpty();
            return;
        }


        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

//        Intent deleteTokenService = new Intent(this, DeleteTokenService.class);
//        startService(deleteTokenService);
    }

    private ArrayList<GuestModel> loadGuestFromDatabase() {
        return DatabaseOperations.getGuests(this, null, null, null);
    }
}
