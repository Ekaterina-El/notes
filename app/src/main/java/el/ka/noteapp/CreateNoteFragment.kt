package el.ka.noteapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import el.ka.noteapp.database.NotesDatabase
import el.ka.noteapp.entities.Notes
import el.ka.noteapp.utils.*
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {
    private lateinit var currentDateTime: String
    var selectedColor = DEFAULT_COLOR


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setColorViewBackground()
        LocalBroadcastManager.getInstance(MAIN_ACTIVITY).registerReceiver(
            broadcastReceiver,
            IntentFilter(BOTTOM_SHEET_ACTION)
        )
        setCurrentDateTime()
        addListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(MAIN_ACTIVITY).unregisterReceiver(broadcastReceiver)
    }

    private fun addListeners() {
        imgDone.setOnClickListener {
            saveNote()
        }

        imgBack.setOnClickListener {
            goHomeFragment()
        }

        imgMore.setOnClickListener {
            val noteBottomSheet = NoteBottomSheet.newInstance()
            noteBottomSheet.show(MAIN_ACTIVITY.supportFragmentManager, "Note Bottom Sheet Fragment")
        }
    }

    private fun setCurrentDateTime() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDateTime = sdf.format(Date())

        et_notes_date_time.text = currentDateTime
    }

    private fun saveNote() {
        if (et_notes_title.text.isNullOrEmpty()) {
            showToast("Title is empty")
        } else if (et_notes_subtitle.text.isNullOrEmpty()) {
            showToast("Subtitle is empty")
        } else if (et_notes_description.text.isNullOrEmpty()) {
            showToast("Description is empty")
        } else {
            saveNoteToDB()
        }
    }

    private fun saveNoteToDB() {
        launch {
            val notes = Notes()
            notes.title = et_notes_title.text.toString()
            notes.dateTime = currentDateTime
            notes.subtitle = et_notes_subtitle.text.toString()
            notes.noteText = et_notes_description.text.toString()
            notes.color = selectedColor

            MAIN_ACTIVITY.let {
                NotesDatabase.getDatabase(it).notesDao().insertNotes(notes)
                et_notes_title.setText("")
                et_notes_subtitle.setText("")
                et_notes_description.setText("")
                et_notes_date_time.text = ""
            }
        }
    }

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("ResourceType")
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(p0: Context?, intent: Intent?) {
            selectedColor = intent!!.getStringExtra("color").toString()
            setColorViewBackground()
        }

    }

    private fun setColorViewBackground() {
        color_view.setBackgroundColor(getColorByIndexName(selectedColor))
    }

}