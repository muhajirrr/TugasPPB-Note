package com.muhajirlatif.note;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Delete
    void delete(Note note);

    @Query("SELECT * from notes ORDER BY id DESC")
    LiveData<List<Note>> getAllNotes();

}
