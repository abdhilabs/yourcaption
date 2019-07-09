package com.abdhilabs.yourcaption.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.abdhilabs.yourcaption.database.DataBaseHelper
import com.abdhilabs.yourcaption.model.Caption
import com.abdhilabs.yourcaption.R

class CaptionRecycleAdapter(private val listCaption: ArrayList<Caption>, private val context1: Context) :
    RecyclerView.Adapter<CaptionRecycleAdapter.CaptionViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CaptionViewHolder {
        //
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.list_saved, p0, false)
        return CaptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CaptionViewHolder, position: Int) {
        holder.caption.text = listCaption[position].isicaption

        holder.itemView.setOnClickListener(View.OnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Coba")
                .setPositiveButton("Copy", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(holder.itemView.context, "Kamu pilih copy", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("Hapus", DialogInterface.OnClickListener { dialog, which ->
                    val DataBaseHelper = DataBaseHelper(context = context1)
                    DataBaseHelper.deleteCaption(caption = listCaption[position])
                    listCaption.removeAt(position)
                    notifyItemRemoved(position)
//                    Toast.makeText(holder.itemView.context,"Kamu pilih tidak", Toast.LENGTH_LONG).show()
                })
                .show()
//            Toast.makeText(holder.itemView.context, "${holder.caption.text}", Toast.LENGTH_SHORT).show()
//
        })
    }

    override fun getItemCount(): Int {
        return listCaption.size
    }


    /**
     *ViewHolder Class
     */

    inner class CaptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var caption: TextView

        init {
            caption = view.findViewById<View>(R.id.tv_saved) as TextView
        }
    }

}