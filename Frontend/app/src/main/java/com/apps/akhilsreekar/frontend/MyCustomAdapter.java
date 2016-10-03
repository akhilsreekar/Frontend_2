package com.apps.akhilsreekar.frontend;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AKHIL on 29-09-2016.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {

    ArrayList<Information> data;
    LayoutInflater inflator;
    static int previousPosition = -1;
    ViewHolder staticHolder;
    static ArrayList<ViewHolder> holding = new ArrayList<ViewHolder>();
    public static final int GRID = 0;
    public static final int LIST = 1;
    Context context;

    public MyCustomAdapter(){

    }


    public MyCustomAdapter(Context context, ArrayList<Information> data) {
        this.context = context;
        this.data = data;
        inflator = LayoutInflater.from(context);
        staticHolder = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewList;
        ViewHolder holder = null;
        switch (MainActivity.TYPE) {
            case LIST:
                viewList = inflator.inflate(R.layout.list_item_row, parent, false);
                holder = new ViewHolder(viewList);
                break;
            case GRID:
                View viewGrid = inflator.inflate(R.layout.list_item_row, parent, false);
                holder = new ViewHolder(viewGrid);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //final Zoomer zoom = new Zoomer();
        holder.tvGameName.setText(data.get(position).title);
        holder.imgGame.setImageResource(data.get(position).imageId);
        holder.imgGame.setBackgroundColor(Data.getBackGroundColor(position));
        holder.card.setBackgroundColor(ContextCompat.getColor(MainActivity.mMainActivity, R.color.transparent));
        holding.add(holder);

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (staticHolder != null) {
                    staticHolder.card.setBackgroundColor(ContextCompat.getColor(MainActivity.mMainActivity, R.color.transparent));
                    holder.card.setBackgroundColor(ContextCompat.getColor(MainActivity.mMainActivity, R.color.selected_card));
                }
                staticHolder = holder;
                previousPosition = position;
                //zoom.set(staticHolder,v);
            }
        });
    }

    public ViewHolder Holdingcard(){
        return staticHolder;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvGameName;
        ImageView imgGame;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGameName = (TextView) itemView.findViewById(R.id.tvGameName);
            imgGame = (ImageView) itemView.findViewById(R.id.imgGame);
            card = (CardView) itemView.findViewById(R.id.card);
        }
    }

}


