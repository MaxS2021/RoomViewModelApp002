package com.example.roomviewmodelapp002.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomviewmodelapp002.R
import com.example.roomviewmodelapp002.data.User
import com.example.roomviewmodelapp002.data.UserViewModel
import com.example.roomviewmodelapp002.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var bind: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.updateFirstNameEt.setText(args.currentUser.firstName)
        bind.updateLastNameEt.setText(args.currentUser.lastName)
        bind.updateAgeEt.setText(args.currentUser.age.toString())

        println(bind.updateFirstNameEt.text)
        println(bind.updateLastNameEt.text)
        println(bind.updateAgeEt.text)

        bind.updateBtn.setOnClickListener {
            updateItem()
        }

        // Add Delete Menu
        setHasOptionsMenu(true)

        return bind.root //inflater.inflate(R.layout.fragment_update, container, false)
    }

    private fun updateItem() {
        val firstName = bind.updateFirstNameEt.text.toString()
        val lastName = bind.updateLastNameEt.text.toString()
        val age = bind.updateAgeEt.text

        if (inputCheck(firstName, lastName, age)) {
            val user = User(args.currentUser.id, firstName, lastName, age.toString().toInt() )
            mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(), "Изменения внесены в базу", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show()
        }


    }

    private fun inputCheck(fistName: String, lastName: String, age: Editable): Boolean {
        return !(fistName.isEmpty() || lastName.isEmpty() || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)

    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Запись успешно удалена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Нет") {_, _ ->}
        builder.setTitle("Удалить запись? :")
        builder.setMessage("Вы уверены, что хотите удалить запись: ${args.currentUser.firstName}?")
        builder.create().show()
    }

}