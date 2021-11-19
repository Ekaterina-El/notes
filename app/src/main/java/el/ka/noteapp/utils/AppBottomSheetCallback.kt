package el.ka.noteapp.utils

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

class AppBottomSheetCallback(
    private val behavior: BottomSheetBehavior<*>,
    private val onDismiss: () -> Unit
) : BottomSheetBehavior.BottomSheetCallback() {
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
                onDismiss
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {

    }

}