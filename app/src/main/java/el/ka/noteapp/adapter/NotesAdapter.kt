package el.ka.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import el.ka.noteapp.R
import el.ka.noteapp.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}

class NotesAdapter(private val notesList: List<Notes>): RecyclerView.Adapter<NotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val noteInfo = notesList[position]
        holder.itemView.tv_note_title.text = noteInfo.title
        holder.itemView.tv_note_description.text = noteInfo.noteText
        holder.itemView.tv_note_date_time.text = noteInfo.dateTime
    }

    override fun getItemCount(): Int = notesList.size
}