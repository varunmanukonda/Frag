package com.example.fragmentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class profilefragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View=  inflater.inflate(R.layout.fragment_profilefragment, container, false)
        val activity = activity as Context


        val homebtn = view.findViewById<Button>(R.id.button1)


        homebtn.setOnClickListener {
            getActivity()?.finish();
        }
        return view
    }


}