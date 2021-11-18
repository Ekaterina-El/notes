package el.ka.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import el.ka.noteapp.adapter.NotesAdapter
import el.ka.noteapp.database.NotesDatabase
import el.ka.noteapp.utils.MAIN_ACTIVITY
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_notes.setHasFixedSize(true)
        rv_notes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        launch {
            MAIN_ACTIVITY.let {
                val notes = NotesDatabase.getDatabase(MAIN_ACTIVITY).notesDao().getAllNotes()
                rv_notes.adapter = NotesAdapter(notes)
            }
        }

        fab_creat_note.setOnClickListener {
            MAIN_ACTIVITY.replaceFragment(CreateNoteFragment.newInstance(), true)
        }
    }
}