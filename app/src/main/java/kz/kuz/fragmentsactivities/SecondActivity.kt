package kz.kuz.fragmentsactivities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SecondActivity : AppCompatActivity() {
    var fm = supportFragmentManager
    var fragment = fm.findFragmentById(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras

        if (fragment == null) {
            fragment = SecondFragment1()

            if (bundle != null) {
                val args = Bundle()
                args.putInt("Number", bundle.getInt("Number"))
                Log.i("NumberProvide", args.getInt("Number").toString())
                fragment?.arguments = args
            }

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment!!)
                    .commit()
        }
    }

    fun changeFragment(newFragment: Fragment) {
        fragment = newFragment
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment!!)
                .commit()
    }
}