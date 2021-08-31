package com.example.fragmentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.io.File
import java.lang.reflect.Type


class RecyclerviewFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyListAdapter.ViewHolder>? = null
    var list: ArrayList<MyListData>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recyclerview, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

//        val fab: View = findViewById(R.id.fab)
        val fab = view?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener { view ->
            val activity = view.context as AppCompatActivity
            val fragmentManager: FragmentManager =
                activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val NAME =registration()
            fragmentTransaction.replace(R.id.details_fragment, NAME)
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        recycler_view.apply {

            list = ArrayList<MyListData>()
            val sharedPreferences = requireActivity()!!.getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val  gson = Gson()
            editor.putString("courses", null)
            editor.apply()
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            (list as ArrayList<MyListData>).add(
                MyListData(
                    R.drawable.download,
                    "email",
                    "name",
                    "gender",
                    "time"
                )
            )
            adapter = MyListAdapter(list as ArrayList<MyListData>)












        }


    }

}