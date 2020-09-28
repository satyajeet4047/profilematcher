package com.example.profilematcher.ui.recyclerviewutil

import androidx.recyclerview.widget.DiffUtil
import com.example.profilematcher.data.model.LocalUserInfo


/*
 Diff util class used to calculate diff between recyclerview old list & new list
 */
class ProfileListDiffUtil(val oldList : ArrayList<LocalUserInfo>, val newList : ArrayList<LocalUserInfo>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int {
      return  oldList.size
    }

    override fun getNewListSize(): Int {
        return  newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition].name.equals( newList[newItemPosition].name)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}