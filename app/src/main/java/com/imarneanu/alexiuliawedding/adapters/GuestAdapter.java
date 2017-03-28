package com.imarneanu.alexiuliawedding.adapters;

import com.imarneanu.alexiuliawedding.R;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iulia on 28/03/2017.
 */

public class GuestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GuestModel> mGuests;

    public GuestAdapter(ArrayList<GuestModel> guests) {
        mGuests = guests;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest, parent,
                false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GuestViewHolder guestViewHolder = (GuestViewHolder) holder;
        guestViewHolder.guestName.setText(mGuests.get(position).name);
        guestViewHolder.guestTimestamp.setText(mGuests.get(position).timestamp);
        guestViewHolder.guestAccessedCounter.setText(String.valueOf(mGuests.get(position).counter));
    }

    @Override
    public int getItemCount() {
        return mGuests.size();
    }

    class GuestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guest_name)
        TextView guestName;
        @BindView(R.id.guest_timestamp)
        TextView guestTimestamp;
        @BindView(R.id.guest_accessed_counter)
        TextView guestAccessedCounter;

        GuestViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
