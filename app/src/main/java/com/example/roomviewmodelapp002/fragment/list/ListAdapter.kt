package com.example.roomviewmodelapp002.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomviewmodelapp002.R
import com.example.roomviewmodelapp002.data.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var  userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        val id_txt = holder.itemView.findViewById<TextView>(R.id.id_txt)
        val firsName_txt = holder.itemView.findViewById<TextView>(R.id.firstName_txt)
        val lastName_txt = holder.itemView.findViewById<TextView>(R.id.lastName_txt)
        val age_txt = holder.itemView.findViewById<TextView>(R.id.age_txt)
        val rowLayout = holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout)

        id_txt.text = currentUser.id.toString()
        firsName_txt.text = currentUser.firstName.toString()
        lastName_txt.text = currentUser.lastName.toString()
        age_txt.text = currentUser.age.toString()

        rowLayout.setOnClickListener {
            // переключение на фрагент UpdateFragment
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}