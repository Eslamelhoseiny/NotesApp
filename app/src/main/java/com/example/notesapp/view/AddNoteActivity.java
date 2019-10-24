package com.example.notesapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.notesapp.R;
import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
    }

    public void onClick(View view) {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        Note note = new Note(title, description);
        NoteRepository.getInstance(this).insertNote(note, new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                finish();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
