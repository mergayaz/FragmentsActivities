package kz.kuz.fragmentsactivities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment1 : Fragment() {
    private lateinit var newFragment: Fragment
    private lateinit var editText: EditText

    // методы фрагмента должны быть открытыми
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.second_fragment1_title)
        val view = inflater.inflate(R.layout.fragment1_second, container, false)
        val number: Int
        val args: Bundle? = arguments
        number = if (args != null) {
            arguments!!.getInt("Number")
        } else {
            0
        }
        editText = view.findViewById(R.id.editNumber)
        editText.requestFocus()

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = number.toString()

        val btnMain = view.findViewById<Button>(R.id.btn_main)
        btnMain.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            val bundle = Bundle()
            var numberText = editText.text.toString()
            numberText = if (numberText.isNotEmpty()) numberText else "0"
            bundle.putInt("Number", numberText.toInt())
            intent.putExtras(bundle)
            //                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            // выше добавление к интенту флага, чтобы новая активность открывалась в новом
            // документе, подчинённом старой активности
            // эта же цель будет достигнута, если в манифесте у добавляемой активности поставить
            // documentLaunchMode = "intoExisting"
            // при этом если та же самая активность будет создана повторно, то новый документ
            // создаваться уже не будет, а будет обновлён прежний документ
            // если нужно, чтобы каждый раз создавался новый документ, тогда одновременно с
            // Intent.FLAG_ACTIVITY_NEW_DOCUMENT нужно добавить ещё один флаг:
            // intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            // либо же в манифесте у добавляемой активности поставить вместо "intoExisting":
            // documentLaunchMode = "always" (подробнее в манифесте)
            startActivity(intent)
        }

        val btnSecond1 = view.findViewById<Button>(R.id.btn_second1)
        btnSecond1.setOnClickListener {
            newFragment = SecondFragment1()
            val args = Bundle()
            var numberText = editText.text.toString()
            numberText = if (numberText.isNotEmpty()) numberText else "0"
            args.putInt("Number", numberText.toInt())
            newFragment.arguments = args
            (activity as SecondActivity).changeFragment(newFragment)
        }

        val btnSecond2 = view.findViewById<Button>(R.id.btn_second2)
        btnSecond2.setOnClickListener {
            newFragment = SecondFragment2()
            val args = Bundle()
            val numberText = editText.text.toString()
            args.putInt("Number", numberText.toInt())
            newFragment.arguments = args
            (activity as SecondActivity).changeFragment(newFragment)
        }

        val btnClose = view.findViewById<Button>(R.id.btn_close)
        btnClose.setOnClickListener { (activity as SecondActivity).finish() }

        return view
    }
}