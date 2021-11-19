package el.ka.noteapp.utils

import android.widget.Toast
import el.ka.noteapp.MainActivity

lateinit var MAIN_ACTIVITY: MainActivity

fun showToast(text: String) {
    Toast.makeText(MAIN_ACTIVITY, text, Toast.LENGTH_SHORT).show()
}

const val BOTTOM_SHEET_ACTION = "bottom_sheet_action"
const val COLOR_EXTRA = "color"
