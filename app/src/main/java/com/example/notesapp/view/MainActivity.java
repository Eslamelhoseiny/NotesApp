package com.example.notesapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notesapp.R;
import com.example.notesapp.adapter.NoteRecyclerAdapter;
import com.example.notesapp.model.Note;
import com.example.notesapp.view_model.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerView recyclerNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerNotes = findViewById(R.id.recyclerNotes);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        subscribeOnNotesLiveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            startActivity(new Intent(this, AddNoteActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void subscribeOnNotesLiveData() {
        noteViewModel.getNoteRepository().getNotes().observe(this, notes -> {
            NoteRecyclerAdapter adapter = new NoteRecyclerAdapter(notes);
            recyclerNotes.setAdapter(adapter);
            Log.d("Test", "Notes Updated");
        });
    }
}
