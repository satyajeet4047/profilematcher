package com.example.profilematcher.ui.bindingutil

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.profilematcher.R
import com.example.profilematcher.util.Constants.Companion.PROFILE_ACCEPT
import com.example.profilematcher.util.Constants.Companion.PROFILE_NO_ACTION
import com.example.profilematcher.util.Constants.Companion.PROFILE_REJECT
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["setProfileImage"])
fun ImageView.setProfileImage(avatarUrl : String?){

    val view = this
    if(avatarUrl != null && avatarUrl.isNotBlank()){

        Picasso.with(context)
            .load(avatarUrl)
            .error(R.drawable.ic_baseline_error_24)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(view, object : Callback {
                override fun onSuccess() { //..image loaded from cache

                }

                override fun onError() { //Try again online if cache failed
                    Picasso.with(context)
                        .load(avatarUrl)
                        .error(R.drawable.ic_baseline_error_24)
                        .into(view, object : Callback {
                            override fun onSuccess() {

                            }
                            override fun onError() {}
                        })
                }
            })
    }
}

@BindingAdapter("updateStatus")
fun TextView.update(status : Int) {

    when (status) {
        PROFILE_NO_ACTION -> {
            this.text = ""
            this.visibility = View.GONE
        }
        PROFILE_ACCEPT -> {
            this.text = resources.getText(R.string.profile_accepted_text)
            this.visibility = View.VISIBLE
            this.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        }
        PROFILE_REJECT -> {
            this.text = resources.getText(R.string.profile_rejected_text)
            this.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            this.visibility = View.VISIBLE

        }
    }
}
@BindingAdapter("updateVisibility")
fun Button.updateVisibility(status : Int) {

    when (status) {
        PROFILE_NO_ACTION -> {
            this.visibility = View.VISIBLE
        }
        PROFILE_ACCEPT -> {
            this.visibility = View.GONE
        }
        PROFILE_REJECT -> {
            this.visibility = View.GONE
        }
    }
}





