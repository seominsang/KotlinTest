package com.mrhi2021.kotlinbnvfragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class Tab1Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    lateinit var et:EditText
    lateinit var tv:TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et= view.findViewById(R.id.et)
        tv= view.findViewById(R.id.tv)

        view.findViewById<Button>(R.id.btn).setOnClickListener {
            //tv.setText(et.getText().toString())
            tv.text= et.text.toString()
            et.setText("")
        }

        et.setOnEditorActionListener { textView, i, keyEvent ->
            tv.text= textView.text
            et.setText("")
            true
        }

    }



}