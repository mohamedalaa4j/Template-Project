package com.am.template.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.data.remote.dto.general.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    val openDrawer = MutableLiveData<Boolean>()
    val profileData = MutableLiveData<Member>()

    private var filterProductsByBrandJob: Job? = null

}