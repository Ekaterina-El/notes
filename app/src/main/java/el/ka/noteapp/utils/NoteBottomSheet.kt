package el.ka.noteapp.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import el.ka.noteapp.R
import kotlinx.android.synthetic.main.fragment_note_bottom_sheets.*

class NoteBottomSheet : BottomSheetDialogFragment() {
    private lateinit var fNotes: ArrayList<FrameLayout>
    private lateinit var colorImages: ArrayList<ImageView>

    companion object {
        fun newInstance(): NoteBottomSheet {
            val fragment = NoteBottomSheet()
            fragment.arguments = Bundle()
            return fragment
        }
    }

    @SuppressLint("RestrictedApi", "InflateParams")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(dialog.context)
            .inflate(R.layout.fragment_note_bottom_sheets, null)
        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = param.behavior

        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(AppBottomSheetCallback(behavior) { dismiss() })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_bottom_sheets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        colorImages = arrayListOf(img_note1, img_note2, img_note3, img_note4, img_note5)
        fNotes = arrayListOf(fNote1, fNote2, fNote3, fNote4, fNote5)
        setListener()
    }

    private fun setListener() {
        for (i in 0 until fNotes.size) {
            fNotes[i].setOnClickListener {
                onSelectColor("color${i+1}")
            }
        }
    }

    private fun onSelectColor(color: String) {
        dissableAllImagesNote()
        val intent = Intent(BOTTOM_SHEET_ACTION)
        intent.putExtra(COLOR_EXTRA, color)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        selectTickFotColor(color)
    }

    private fun selectTickFotColor(color: String) {
        val num = color.substring(5).toInt()
        colorImages[num - 1].setImageResource(R.drawable.ic_tick)
    }


    private fun dissableAllImagesNote() {
        colorImages.forEach {
            it.setImageResource(0)
        }
    }
}