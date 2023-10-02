package com.am.template.presentation.ui.main.tablayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.am.template.R
import com.am.template.data.local.models.ReportModel
import com.am.template.databinding.FragmentWeekBinding
import com.am.template.presentation.adapters.ReportsRvAdapter
import com.am.template.presentation.viewmodel.SharedViewModel
import com.am.template.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeekFragment : Fragment(R.layout.fragment_week), ReportsRvAdapter.OnItemClickListener {
    private lateinit var binding: FragmentWeekBinding

    @Inject
    lateinit var reportsRvAdapter: ReportsRvAdapter
    private val viewModelShared: SharedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeekBinding.bind(view)

        initializeRv()
        fetchDataState()
    }

    private fun initializeRv() {
        reportsRvAdapter.setListener(this@WeekFragment)
        binding.rvReports.adapter = reportsRvAdapter
    }


    private fun fetchDataState() {
        lifecycleScope.launchWhenCreated {
            viewModelShared.dataState.collect { data ->
                reportsRvAdapter.submitList(data)
            }
        }
    }

    override fun onReportClick(model: ReportModel) {
        showToast(model.title)
    }
}