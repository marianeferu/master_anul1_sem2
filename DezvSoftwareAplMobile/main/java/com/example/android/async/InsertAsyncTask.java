package com.example.android.async;

import android.os.AsyncTask;

import com.example.android.database.NoteDao;
import com.example.android.model.Note;

public class InsertAsyncTask  extends AsyncTask<Note, Void, Void> {

    private NoteDao mNoteDao;

    public InsertAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.insertNotes(notes);
        return null;
    }

}