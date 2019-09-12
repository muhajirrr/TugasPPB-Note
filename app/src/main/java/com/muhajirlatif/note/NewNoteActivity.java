package com.muhajirlatif.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_NOTE = "reply_note";

    private EditText etTitle, etBody;
    private Button btnSave;
    private ImageButton btnDelete;
    private Note note;

    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        note = getIntent().getParcelableExtra(MainActivity.EXTRA_NOTE);
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        if (note != null) {
            etTitle.setText(note.getTitle());
            etBody.setText(note.getBody());
            getSupportActionBar().setTitle(note.getTitle());
        } else {
            getSupportActionBar().setTitle("New Note");
        }

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                String title = etTitle.getText().toString();
                String body = etBody.getText().toString();

                if (note != null) {
                    note.setTitle(title);
                    note.setBody(body);
                } else {
                    note = new Note(title, body);
                }

                Intent replyIntent = new Intent();

                if (title.isEmpty() && body.isEmpty()) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(EXTRA_NOTE, note);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

            case R.id.btnDelete:
                if (note != null) {
                    mNoteViewModel.delete(note);
                }
                finish();

            default:

        }
    }
}
