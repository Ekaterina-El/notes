package el.ka.noteapp.dao

import androidx.room.*
import el.ka.noteapp.entities.Notes

@Dao
interface NotesDao {
    @get: Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Notes)

    @Delete
    fun deleteNote(note: Notes)
}