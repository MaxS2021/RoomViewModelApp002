package com.example.roomviewmodelapp002.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomviewmodelapp002.R
import com.example.roomviewmodelapp002.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var bind: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentListBinding.inflate(inflater, container, false)

        bind.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return bind.root   //inflater.inflate(R.layout.fragment_list, container, false)
    }


}