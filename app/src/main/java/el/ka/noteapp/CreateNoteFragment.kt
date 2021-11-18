package el.ka.noteapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import el.ka.noteapp.database.NotesDatabase
import el.ka.noteapp.entities.Notes
import el.ka.noteapp.utils.MAIN_ACTIVITY
import el.ka.noteapp.utils.showToast
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {
    private lateinit var currentDateTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDateTime = sdf.format(Date())

        et_notes_date_time.text = currentDateTime

        imgDone.setOnClickListener {
            // save
            saveNote()
        }

        imgBack.setOnClickListener {
            // back
            MAIN_ACTIVITY.replaceFragment(HomeFragment.newInstance(), false)
        }
    }

    private fun saveNote() {
        if (et_notes_title.text.isNullOrEmpty()) {
            showToast("Title is empty")
        } else if (et_notes_subtitle.text.isNullOrEmpty()) {
            showToast("Subtitle is empty")
        } else if (et_notes_description.text.isNullOrEmpty()) {
            showToast("Description is empty")
        } else {
            launch {
                val notes = Notes()
                notes.title = et_notes_title.text.toString()
                notes.dateTime = currentDateTime
                notes.subtitle = et_notes_subtitle.text.toString()
                notes.noteText = et_notes_description.text.toString()

                MAIN_ACTIVITY.let {
                    NotesDatabase.getDatabase(it).notesDao().insertNotes(notes)
                    et_notes_title.setText("")
                    et_notes_subtitle.setText("")
                    et_notes_description.setText("")
                    et_notes_date_time.text = ""
                }
            }
        }
    }

}