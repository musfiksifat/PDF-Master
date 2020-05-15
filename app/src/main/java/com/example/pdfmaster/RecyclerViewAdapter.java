package com.example.pdfmaster;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FileLayoutHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private IViewHolderClickListener mOnClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context mContext, IViewHolderClickListener mOnClickListener) {
        this.mContext = mContext;
        this.mOnClickListener = mOnClickListener;
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerViewAdapter.FileLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.files_list, parent, false);

        return new FileLayoutHolder(v, mOnClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.FileLayoutHolder holder, int position) {

        try {
            holder.fileTitle.setText(FileConstant.allFileList.get(position).getName());
            //we will load thumbnail using glid library
            Uri uri = Uri.fromFile(FileConstant.allFileList.get(position));

            //Glide.with(mContext)
            //.load(uri).thumbnail(0.1f).into(holder.thumbnail);

        } catch (NullPointerException e) {
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return FileConstant.allFileList.size();
    }

    public interface IViewHolderClickListener {
        void onItemClick(int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class FileLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        ImageView thumbnail;
        TextView fileTitle;
        ImageView ic_more_btn;
        IViewHolderClickListener mListener;

        public FileLayoutHolder(@NonNull View itemView, IViewHolderClickListener listener) {
            super(itemView);
            mListener = listener;

            thumbnail = itemView.findViewById(R.id.thumbnail);
            fileTitle = itemView.findViewById(R.id.filetitle);
            ic_more_btn = itemView.findViewById(R.id.ic_more_btn);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnClickListener.onItemClick(getAdapterPosition());
        }
    }
}

