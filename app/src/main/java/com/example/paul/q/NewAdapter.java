package com.example.paul.q;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PAUL on 09-05-2018.
 */
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ModelClass> list;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public NewAdapter(Context context,ArrayList<ModelClass> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelClass current= list.get(position);

        String imageurl=current.getmImageUrl();
        String creatorname=current.getmCreator();
        int likes=current.getmLikes();

       holder.Textcreator.setText(creatorname);
       holder.Textlikes.setText("Likes: " +likes);
        Picasso.with(context).load(imageurl).fit().centerInside().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView Textcreator;
        public TextView Textlikes;

        public MyViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            Textcreator=itemView.findViewById(R.id.user_text);
            Textlikes=itemView.findViewById(R.id.likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( mListener != null){
                        int position = getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
