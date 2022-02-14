package com.example.android.async;

import android.os.AsyncTask;

import com.example.android.database.NoteDao;
import com.example.android.model.Note;

public class UpdateAsyncTask  extends AsyncTask<Note, Void, Void> {

    private NoteDao mNoteDao;

    public UpdateAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.updateNotes(notes);
        return null;
    }

}