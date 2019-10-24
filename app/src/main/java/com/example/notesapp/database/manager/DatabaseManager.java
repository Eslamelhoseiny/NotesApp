package com.example.notesapp.database.manager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.database.dao.NoteDao;
import com.example.notesapp.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    private static DatabaseManager databaseManager;

    public abstract NoteDao getNoteDao();

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null)
            databaseManager = Room.databaseBuilder(context, DatabaseManager.class, "NotesDB").fallbackToDestructiveMigration().build();
        return databaseManager;
    }
}
