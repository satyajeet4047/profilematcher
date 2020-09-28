package com.example.profilematcher.ui.recyclerviewutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.profilematcher.data.model.LocalUserInfo
import com.example.profilematcher.data.model.UpdateItemInfo
import com.example.profilematcher.databinding.ProfileListItemBinding

/*
    used to populate recyclerview with items
 */
class ProfileListAdapter constructor(val onProfileAction: OnProfileAction) : RecyclerView.Adapter<ProfileListAdapter.DataViewHolder>()  {

    var  userList  = ArrayList<LocalUserInfo>()

    class DataViewHolder(val binding: ProfileListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userInfo: LocalUserInfo, position: Int, onProfileAction: OnProfileAction){
            binding.userInfo = userInfo
            binding.position = position
            binding.profileActionListener = onProfileAction
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProfileListItemBinding.inflate(inflater)
        return DataViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
      return  userList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userList.get(position),position,onProfileAction)
    }


    fun setData(list: ArrayList<LocalUserInfo>){
        val diffUtil =
            ProfileListDiffUtil(
                userList,
                list
            )
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.userList.clear()
        this.userList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(updateItemInfo: UpdateItemInfo){
        userList[updateItemInfo.postion] = updateItemInfo.localUserInfo
        notifyItemChanged(updateItemInfo.postion)
    }

    interface  OnProfileAction {

        fun onProfileAccept(position: Int, userInfo: LocalUserInfo)
        fun onProfileReject(position: Int, userInfo: LocalUserInfo)
    }
}