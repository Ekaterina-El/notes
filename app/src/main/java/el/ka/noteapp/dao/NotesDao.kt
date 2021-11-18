package el.ka.noteapp.dao

import androidx.room.*
import el.ka.noteapp.entities.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}