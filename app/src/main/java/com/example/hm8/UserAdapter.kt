package com.example.hm8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hm8.R
import com.example.hm8.User

class UserAdapter(val onItemClickLIstener : OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    var dataset = listOf<User>()

    interface OnItemClickListener {
        fun onClick(id : Long) : Unit
    }


    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val click = view.setOnClickListener{
            onItemClickLIstener.onClick(dataset[adapterPosition].id!!)
        }
        val photo = view.findViewById<ImageView>(R.id.userImage)
        val name = view.findViewById<TextView>(R.id.userName)
        val email = view.findViewById<TextView>(R.id.userEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = dataset[position].firstName
        holder.email.text = dataset[position].email
        Glide.with(holder.itemView)
            .load(dataset[position].avatar)
            .centerCrop()
            .into(holder.photo)
    }

    override fun getItemCount() = dataset.size

    fun setData(list : List<User>) {
        dataset = list
        notifyDataSetChanged()
    }
}