package com.example.shifttestexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shifttestexample.databinding.UserListBinding
import com.example.shifttestexample.pojo.PersonInfo
import com.squareup.picasso.Picasso

class UserRecyclerViewAdapter : RecyclerView.Adapter<UserRecyclerViewAdapter.UserInfoViewHolder>() {

    var oncLickListener: onClickListener?=null
    lateinit var onreachEnd: onReachEnd
    class UserInfoViewHolder(private var binding: UserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var tvName = binding.tvName
        var tvCity = binding.tvCity
        var image = binding.imageLogo
        var tvPhone=binding.tvPhone
    }

    var persona:List<PersonInfo> = listOf()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        val binding=UserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return persona.size
    }


    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        val person =persona.get(position)
        Picasso.get()
            .load(person.picture.large)
            .into(holder.image)
        holder.tvCity.text=person.location.city
        holder.tvName.text=person.name.first+" "+person.name.last
        holder.tvPhone.text=person.phone
        holder.itemView.setOnClickListener {
            oncLickListener?.onClick(person)
        }
        if(position==persona.size-10)
        {
            onreachEnd.onReachEnd()
        }
    }

    interface onClickListener{
        fun onClick(personInfo: PersonInfo)
    }
    interface onReachEnd{
        fun onReachEnd()
    }
}