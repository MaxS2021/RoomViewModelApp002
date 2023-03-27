package com.example.roomviewmodelapp002.fragment.update

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.roomviewmodelapp002.R
import com.example.roomviewmodelapp002.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var bind: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentUpdateBinding.inflate(inflater, container, false)

        bind.updateFirstNameEt.setText(args.currentUser.firstName)
        bind.updateLastNameEt.setText(args.currentUser.lastName)
        bind.updateAgeEt.setText(args.currentUser.age.toString())

        bind.updateBtn.setOnClickListener {
            updateItem()
        }

        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    private fun updateItem() {
        val firstName = bind.updateFirstNameEt.text.toString()


    }

    private fun inputCheck(fistName: String, lastName: String, age: Editable): Boolean {
        return !(fistName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }

}