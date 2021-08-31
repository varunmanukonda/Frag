package com.example.fragmentation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_registrationfragment.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context as Context1


@Suppress("UNREACHABLE_CODE")
class registration : Fragment() {
    var list: ArrayList<MyListData>? = null
    private val myContext: FragmentActivity? = null
    private lateinit var PersonName : EditText
    private lateinit var email: EditText
    private lateinit var button2: Button
    private lateinit var button_date_1: Button
    lateinit var date: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var mPickTimeBtn : Button
    private lateinit var textView: TextView
    var cal = Calendar.getInstance()
    private val courseModalArrayList: ArrayList<MyListData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_registrationfragment, container, false)
        val activity = activity as Context1


        val PersonName = view.findViewById<EditText>(R.id.PersonName)
        val email = view.findViewById<EditText>(R.id.email)
        button2 = view.findViewById<Button>(R.id.button2)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGrp)
        val button_date_1 = view.findViewById<Button>(R.id.button_date_1)
         date = view.findViewById<TextView>(R.id.date)
        val mPickTimeBtn = view.findViewById<Button>(R.id.pickTimeBtn)
        val textView = view.findViewById<TextView>(R.id.timeTv)
        val context = activity as AppCompatActivity

        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        button_date_1!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        button2.setOnClickListener{
            val Name =  PersonName.text.toString().trim()
            val Email =  email.text.toString().trim()
            if (Name.isEmpty()){
                PersonName.error="User name required"

            } else if(Name.length< 5){
                PersonName.error="Name is too short"
            }
            else if (Email.isEmpty()){
                email.error="Email required"

            }
            else if (!email.text.toString().contains("@gmail.com")){
                email.setError("Enter valid Email id !")
            }
            else if (!radioButton4.isChecked() and  !radioButton5.isChecked()){
                Toast.makeText(getActivity(), "Please select a gender !",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            else {

                if(radioButton4.isChecked){
                    sendUserData(Name,Email,"Male",date.text.toString(),textView.text.toString())
                }else{
                    sendUserData(Name,Email,"Female",date.text.toString(),textView.text.toString())

                }
            }

}







        return view
    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date!!.text = sdf.format(cal.getTime())
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun sendUserData(username: String, useremail: String, gender: String, date:String, time:String) {

        Log.e("mounika",username+useremail+gender+date+time)


        (list as ArrayList<MyListData>).add(MyListData(R.drawable.download,useremail,username,gender,time))




        val sharedPreferences = requireActivity()!!.getPreferences(Context1.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val  gson = Gson()
        val jsonCars: String = gson.toJson(list)
        Log.d("TAG", "jsonCars = $jsonCars")
        editor.putString("courses", jsonCars)
        editor.apply()
        editor.commit()


        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.details_fragment, RecyclerviewFragment())
        transaction.commit()

    }


}




