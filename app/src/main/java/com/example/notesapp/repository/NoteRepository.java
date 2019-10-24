package com.example.notesapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.notesapp.database.dao.NoteDao;
import com.example.notesapp.database.manager.DatabaseManager;
import com.example.notesapp.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {

    private static NoteRepository noteRepository;
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    private NoteRepository(Context context) {
        if (noteDao == null)
            noteDao = DatabaseManager.getInstance(context).getNoteDao();
        if (notes == null)
            notes = noteDao.getAll();
    }

    public static NoteRepository getInstance(Context context) {
        if (noteRepository == null)
            noteRepository = new NoteRepository(context);
        return noteRepository;
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        Completable.fromRunnable(() -> noteDao.insert(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void updateNote(Note note) {
        Completable.fromRunnable(() -> noteDao.update(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
