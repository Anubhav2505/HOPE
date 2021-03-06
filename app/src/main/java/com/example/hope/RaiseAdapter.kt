package com.example.hope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RaiseAdapter(private val RequestList: ArrayList<RaiseRequest>) : RecyclerView.Adapter<RaiseAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaiseAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.raise_request_cardview, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RaiseAdapter.MyViewHolder, position: Int) {
        val appoint: RaiseRequest =RequestList[position]
        holder.BloodGroup.text= appoint.Blood_Group;
        holder.patientName.text=appoint.Patient_Name;

    }

    override fun getItemCount(): Int {
        return RequestList.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val BloodGroup : TextView =itemView.findViewById(R.id.bloodgrp);
        val patientName : TextView = itemView.findViewById(R.id.patient)


    }
}


