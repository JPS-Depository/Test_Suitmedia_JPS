package com.jps.test_suitmedia_jps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder> {
    private Context mContext;
    private ArrayList<GuestItem> mGuestList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public GuestAdapter(Context context, ArrayList<GuestItem> guestList)
    {
        mContext = context;
        mGuestList = guestList;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_card, parent,false);
        return new GuestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        GuestItem currentItem = mGuestList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String first = currentItem.getFirst();

        holder.mTextViewGuest.setText(first);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mGuestImage);
    }

    @Override
    public int getItemCount() {
        return mGuestList.size();
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder{

        public ImageView mGuestImage;
        public TextView mTextViewGuest;
        public GuestViewHolder(View itemView) {
            super(itemView);
            mGuestImage = itemView.findViewById(R.id.guest_image);
            mTextViewGuest = itemView.findViewById(R.id.text_view_guest);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!= null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
