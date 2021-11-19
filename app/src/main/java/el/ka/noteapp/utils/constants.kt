package el.ka.noteapp.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.widget.Toast
import el.ka.noteapp.HomeFragment
import el.ka.noteapp.MainActivity
import el.ka.noteapp.R

lateinit var MAIN_ACTIVITY: MainActivity

fun showToast(text: String) {
    Toast.makeText(MAIN_ACTIVITY, text, Toast.LENGTH_SHORT).show()
}

fun goHomeFragment() {
    MAIN_ACTIVITY.replaceFragment(HomeFragment.newInstance(), false)
}

const val BOTTOM_SHEET_ACTION = "bottom_sheet_action"
const val COLOR_EXTRA = "color"
const val DEFAULT_COLOR = "default"

lateinit var resources: Resources
@SuppressLint("ResourceType")
lateinit var NOTE_COLORS: ArrayList<String>

fun updateColorsArray() {
    resources = MAIN_ACTIVITY.resources
    @SuppressLint("ResourceType")
    NOTE_COLORS = arrayListOf<String>(
        resources.getString(R.color.default_note_color),
        resources.getString(R.color.note_color1),
        resources.getString(R.color.note_color2),
        resources.getString(R.color.note_color3),
        resources.getString(R.color.note_color4),
        resources.getString(R.color.note_color5),
    )
}

fun getColorByIndexName(indexName: String): Int {
    val colorString = if (indexName == DEFAULT_COLOR) {
        NOTE_COLORS[0]
    } else {
        val colorNum = indexName.substring(5).toInt()
        NOTE_COLORS[colorNum]
    }
    return Color.parseColor(colorString)
}