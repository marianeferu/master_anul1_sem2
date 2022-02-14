package com.example.android.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.android.R;
import com.example.android.model.Note;
import com.example.android.util.Utility;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "NotesRecyclerAdapter";

    private boolean firstInstance;
    private ArrayList<Note> notesList;
            ArrayList<Note> notesListAll;
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notesList, OnNoteListener onNoteListener) {
        this.notesList = notesList;
        this.mOnNoteListener = onNoteListener;
        this.notesListAll = new ArrayList<>();
        notesListAll.addAll(notesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            String month = notesList.get(position).getTimestamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = notesList.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            holder.timestamp.setText(timestamp);
            holder.title.setText(notesList.get(position).getTitle());
        } catch (NullPointerException e) {
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    @Override
    public Filter getFilter() { return myFilter; }

    Filter myFilter = new Filter() {

        //runs on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (firstInstance) {
                notesList.clear();
            }
            notesList.addAll(notesListAll);

            List<Note> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {

                filteredList.addAll(notesList);
            } else {
                for (Note note : notesList) {

                    if (note.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(note);

                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //runs on a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (! firstInstance) {
                notesListAll.addAll(notesList);
            }
            firstInstance = true;

            notesList.clear();
            notesList.addAll((Collection <? extends Note>) filterResults.values );
            notifyDataSetChanged();

        }
    };



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timestamp, title;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            title = itemView.findViewById(R.id.note_title);
            mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}


