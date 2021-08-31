package com.example.fragmentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView


var f_manager: FragmentManager? = null
class MyListAdapter(private val listdata: MutableList<MyListData>) :
    RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.itemlayout, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val myListData = listdata!![position]
        holder.textView.text = listdata[position].description
        holder.imageView.setImageResource(listdata[position].imgId)
        holder.textView1.text = listdata[position].email
        holder.textView2.text = listdata[position].radioGroup
        holder.time.text = listdata[position].time







        holder.imageView.setOnClickListener { view ->


            val activity = view.context as AppCompatActivity
            val fragmentManager: FragmentManager =
                activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val NAME = profilefragment()
            fragmentTransaction.replace(R.id.details_fragment, NAME)
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }

    interface FragmentCommunication {
        fun respond(position: Int, name: String?, job: String?)
    }


    override fun getItemCount(): Int {
        return listdata!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView1: TextView
        var textView2: TextView
        var textView: TextView
        var time:TextView

        var relativeLayout: RelativeLayout

        init {

            imageView = itemView.findViewById<View>(R.id.item_image) as ImageView
            textView = itemView.findViewById<View>(R.id.item_title) as TextView
            textView1 = itemView.findViewById<View>(R.id.item_detail) as TextView
            textView2 = itemView.findViewById<View>(R.id.item_detail2) as TextView
            time = itemView.findViewById<View>(R.id.time) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout

        }
    }


}