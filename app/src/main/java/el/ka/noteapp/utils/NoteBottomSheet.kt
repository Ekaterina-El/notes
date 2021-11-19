package el.ka.noteapp.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import el.ka.noteapp.R
import kotlinx.android.synthetic.main.fragment_note_bottom_sheets.*

class NoteBottomSheet : BottomSheetDialogFragment() {
    var selectedColor = "#171C26"

    companion object {
        fun newInstance(): NoteBottomSheet {
            val args = Bundle()
            val fragment = NoteBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(dialog.context)
            .inflate(R.layout.fragment_note_bottom_sheets, null)
        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = param.behavior

        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = ""
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            state = "DRAGGING"
                        }

                        BottomSheetBehavior.STATE_SETTLING -> {
                            state = "SETTLING"
                        }

                        BottomSheetBehavior.STATE_EXPANDED -> {
                            state = "EXPANDED"
                        }

                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            state = "COLLAPSED"
                        }

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            state = "HIDDEN"
                            dismiss()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

            })
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

        setListener()
    }

    @SuppressLint("NewApi")
    private fun setListener() {
        fNote1.setOnClickListener {
            dissableAllImagesNote()
            selectedColor = MAIN_ACTIVITY.getColor(R.color.note_color1).toString()
            img_note1.setImageResource(R.drawable.ic_tick)

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("color", "color1")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        fNote2.setOnClickListener {
            dissableAllImagesNote()
            selectedColor = requireActivity().getColor(R.color.note_color2).toString()
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("color", "color2")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        fNote3.setOnClickListener {
            dissableAllImagesNote()
            selectedColor = requireActivity().getColor(R.color.note_color3).toString()
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("color", "color3")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        fNote4.setOnClickListener {
            dissableAllImagesNote()
            selectedColor = requireActivity().getColor(R.color.note_color4).toString()
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("color", "color4")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

        fNote5.setOnClickListener {
            dissableAllImagesNote()
            selectedColor = requireActivity().getColor(R.color.note_color5).toString()
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("color", "color5")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
    }

    private fun dissableAllImagesNote() {
        img_note1.setImageResource(0)
        img_note2.setImageResource(0)
        img_note3.setImageResource(0)
        img_note4.setImageResource(0)
        img_note5.setImageResource(0)
    }
}