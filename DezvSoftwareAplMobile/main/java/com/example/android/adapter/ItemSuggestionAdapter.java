package com.example.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;

import com.example.android.model.ItemSuggestion;

import java.util.ArrayList;

public class ItemSuggestionAdapter extends RecyclerView.Adapter<ItemSuggestionAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ItemSuggestion> mExampleList;


    public ItemSuggestionAdapter(Context context, ArrayList<ItemSuggestion> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.itemsuggestion, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ItemSuggestion currentItem = mExampleList.get(position);



        //  String categoryId = currentItem.getCategoryId();
        // String id = currentItem.getId();
        String name = currentItem.getName();


        //  holder.mTextViewcategoryId.setText(categoryId);
        //   holder.mTextViewid.setText(id);
        holder.mTextViewName.setText(name);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        //public TextView mTextViewcategoryId;
        //public TextView mTextViewid;
        public TextView mTextViewName;


        public ExampleViewHolder(View itemView) {
            super(itemView);

            //mTextViewcategoryId = itemView.findViewById(R.id.text_view_categoryId);
            //mTextViewid = itemView.findViewById(R.id.text_view_id);
            mTextViewName = itemView.findViewById(R.id.text_view_name);


        }
    }
}
