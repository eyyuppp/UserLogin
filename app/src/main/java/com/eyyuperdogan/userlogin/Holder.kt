package com.eyyuperdogan.userlogin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.eyyuperdogan.userlogin.databinding.RecyclerRowBinding
class Holder (val registerList:ArrayList<Know>):RecyclerView.Adapter<Holder.RegisterHolder>(){
    class RegisterHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RegisterHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: RegisterHolder, position: Int) {
        holder.binding.textViewRegister1.text = "username: " + registerList.get(position).username + "  \npasword:" + registerList.get(position).password
        holder.itemView.setOnClickListener(){
          var intent=Intent(holder.itemView.context,WelcomeUser::class.java)
            intent.putExtra("username",registerList.get(position).username)
            intent.putExtra("password",registerList.get(position).password)
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return registerList.size    }
}