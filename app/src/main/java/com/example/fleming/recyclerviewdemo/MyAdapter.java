package com.example.fleming.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * MyAdapter
 * Created by Fleming on 2016/11/15.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] dataset;
    private List<Integer> mHights;
    private boolean isStaggered;

    public MyAdapter(String[] dataset, boolean isStaggered) {
        this.dataset = dataset;
        this.isStaggered = isStaggered;

        mHights = new ArrayList<>();
        for (int i = 0; i < dataset.length; i++) {
            mHights.add((int) (100 + Math.random()*300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.getImageView().getLayoutParams();
        if (isStaggered) {
            lp.height = mHights.get(position);
        } else {
            lp.height = 80;
        }
        holder.getTextView().setLayoutParams(lp);
        holder.getTextView().setText(dataset[position]);

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view, holder.getAdapterPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickLitener.onItemLongClick(view, holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        private TextView getTextView() {
            return mTextView;
        }

        public ImageView getImageView() {
            return mImageView;
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener listener) {
        mOnItemClickLitener = listener;
    }
}
