package el.ka.noteapp.utils

import android.widget.Toast
import el.ka.noteapp.MainActivity

lateinit var MAIN_ACTIVITY: MainActivity

fun showToast(text: String) {
    Toast.makeText(MAIN_ACTIVITY, text, Toast.LENGTH_SHORT).show()
}