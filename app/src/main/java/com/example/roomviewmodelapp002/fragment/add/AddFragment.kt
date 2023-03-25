package com.example.roomviewmodelapp002.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomviewmodelapp002.R
import com.example.roomviewmodelapp002.data.User
import com.example.roomviewmodelapp002.data.UserViewModel
import com.example.roomviewmodelapp002.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var bind: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentAddBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.addBtn.setOnClickListener {
            insertDataToDatabse()
        }

        return bind.root   //inflater.inflate(R.layout.fragment_add, container, false)
    }

    private fun insertDataToDatabse() {
        val fistName = bind.addFirstNameEt.text.toString()
        val lastName = bind.addLastNameEt.text.toString()
        val age = bind.addAgeEt.text

        if (inputCheck(fistName, lastName, age)) {
            val user = User(0, fistName, lastName, age.toString().toInt())
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Запись добавлена в базу", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(fistName: String, lastName: String, age: Editable): Boolean {
        return !(fistName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }

}