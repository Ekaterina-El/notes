package el.ka.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import el.ka.noteapp.utils.MAIN_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN_ACTIVITY = this
        replaceFragment(HomeFragment.newInstance(), true)
    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            if (isTransition) {
                setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
            }
            replace(R.id.frame_layout, fragment).addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }


}