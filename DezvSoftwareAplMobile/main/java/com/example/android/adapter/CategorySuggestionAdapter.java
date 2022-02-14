package com.example.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.model.CategorySuggestion;

import java.util.ArrayList;

public class CategorySuggestionAdapter extends RecyclerView.Adapter<CategorySuggestionAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<CategorySuggestion> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CategorySuggestionAdapter(Context context, ArrayList<CategorySuggestion> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.categorysuggestion, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        CategorySuggestion currentItem = mExampleList.get(position);

        //String Id = currentItem.getId();
        String Name = currentItem.getName();

        //holder.mTextViewId.setText(Id);
        holder.mTextViewName.setText(Name);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewId;
        public TextView mTextViewName;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.text_view_name);
            // mTextViewId = itemView.findViewById(R.id.text_view_id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
