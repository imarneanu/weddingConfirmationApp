package com.imarneanu.alexiuliawedding.adapters;

import com.imarneanu.alexiuliawedding.R;
import com.imarneanu.alexiuliawedding.data.models.GuestModel;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

    public GuestAdapter() {
        mGuests = new ArrayList<>(0);
    }

    public void setGuests(ArrayList<GuestModel> guests) {
        mGuests.clear();
        mGuests.addAll(guests);
        notifyDataSetChanged();
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
        GuestModel guest = mGuests.get(position);
        guestViewHolder.guestName.setText(getGuestNames(guest));
        guestViewHolder.guestTimestamp.setText(guest.timestamp);
        guestViewHolder.guestAccessedCounter.setText(String.valueOf(guest.counter));
    }

    private String getGuestNames(GuestModel guest) {
        if (TextUtils.isEmpty(guest.plusOneName)) {
            return guest.guestName;
        }
        return guest.guestName.concat("&").concat(guest.plusOneName);
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
