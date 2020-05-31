package com.example.trial

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_file.view.*

class customAdapter(val context:Context,val customlist:ArrayList<Custom>):RecyclerView.Adapter<customAdapter.myViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_file,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customlist.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val user:Custom = customlist[position]
        holder.textviename.text = user.name
        holder.textviewname2.text=user.name2
    }
    inner class myViewHolder(item_view:View):RecyclerView.ViewHolder(item_view){
       val textviename = item_view.findViewById(R.id.tvListView)as TextView
        val textviewname2= item_view.findViewById(R.id.tvListView1)as TextView
    }

}