package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ListRowBinding


class myadap (val activity:MainActivity,val Notes:ArrayList<String>):RecyclerView.Adapter<myadap.ItemViewHolder>(){

    class ItemViewHolder(val binding:ListRowBinding ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListRowBinding.inflate( LayoutInflater.from(parent.context),parent
                ,false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
 val NOTE = Notes[position]
        holder.binding.apply {
            textv.text=NOTE
            imgbrvEdit.setOnClickListener {
           activity.openwendow(NOTE)
            }

            imgbrvdelete.setOnClickListener {
                activity.delete(NOTE)


            }

        }
    }

    override fun getItemCount() = Notes.size

}
