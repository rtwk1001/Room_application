package incture.com.roomexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import incture.com.roomexample.models.Note;

/**
 * Created by Ritwik.Jain on 3/20/2018.
 */
@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Query("SELECT * FROM notes where title LIKE  :title")
    Note findByName(String title);

    @Query("SELECT COUNT(*) from notes")
    int countNotes();

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Update
    void updateNote(Note note);
}
