package com.example.profilematcher.ui.profiles

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profilematcher.R
import com.example.profilematcher.data.model.LocalUserInfo
import com.example.profilematcher.data.model.UpdateItemInfo
import com.example.profilematcher.databinding.ProfilesFragmentBinding
import com.example.profilematcher.ui.recyclerviewutil.CustomDecoration
import com.example.profilematcher.ui.recyclerviewutil.ProfileListAdapter
import com.example.profilematcher.util.Constants.Companion.PROFILE_ACCEPT
import com.example.profilematcher.util.Constants.Companion.PROFILE_REJECT
import com.example.profilematcher.util.RequestStatus
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.profiles_fragment.*
import javax.inject.Inject


/*
 Fragment which shows profiles list
 */
class ProfilesFragment : Fragment(), ProfileListAdapter.OnProfileAction {

    companion object {
        fun newInstance() = ProfilesFragment()
    }

    private  var alertDialog : AlertDialog?  = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private  val viewModel: ProfilesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ProfilesViewModel::class.java]
    }

    lateinit  var binding : ProfilesFragmentBinding
    lateinit var profileAdapter : ProfileListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= DataBindingUtil.inflate<ProfilesFragmentBinding>(inflater,R.layout.profiles_fragment, container, false)
        profileAdapter =
            ProfileListAdapter(
                this
            )
        with(binding.rvProfiles){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            addItemDecoration(CustomDecoration(60))
            adapter = profileAdapter

        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewModel.fetchUserProfiles()
    }


    private fun setupUI() {

        viewModel.getResponse().observe(viewLifecycleOwner, Observer<List<LocalUserInfo>>{
                api : List<LocalUserInfo>? ->
            api?.let { profileAdapter.setData(it as ArrayList<LocalUserInfo>) }

        })

        viewModel.getStatus().observe(viewLifecycleOwner, Observer<RequestStatus> {
                requestStatus: RequestStatus? ->
            when(requestStatus){
                RequestStatus.IN_PROGRESS ->  onRequestInProgress()
                RequestStatus.SUCCESS ->  onRequestSuccess()
                RequestStatus.FAILURE -> onRequestError()
            }
        })

        viewModel.getProfileStatus().observe(viewLifecycleOwner, Observer<UpdateItemInfo> {

                profileAdapter.updateItem(it)

        })
    }


    override fun onProfileAccept(position: Int,userInfo: LocalUserInfo) {
        userInfo.status = PROFILE_ACCEPT
        viewModel.updateUserStatus(position,userInfo)
    }

    override fun onProfileReject(position: Int, userInfo: LocalUserInfo) {
        userInfo.status = PROFILE_REJECT
        viewModel.updateUserStatus(position,userInfo)
    }

    private fun onRequestInProgress(){
        showProgressbar(true)
    }

    private fun onRequestSuccess(){
        showProgressbar(false)
    }

    private fun onRequestError() {

        showProgressbar(false)
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.request_error_dialog_title))
            .setMessage(getString(R.string.request_error_dialog_message))
            .setPositiveButton(getString(R.string.request_error_dialog_ok_btn)) { dialog, whichButton ->
                dialog.dismiss()
            }
            .setCancelable(false)

        alertDialog = builder.create()
        alertDialog?.show()

    }

    private fun showProgressbar(visibility : Boolean?){

        when(visibility){
            true -> progressBar.visibility = View.VISIBLE
            else -> progressBar.visibility = View.GONE
        }
    }


}