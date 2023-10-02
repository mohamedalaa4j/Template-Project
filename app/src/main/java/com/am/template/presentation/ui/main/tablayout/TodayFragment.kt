package com.am.template.presentation.ui.main.tablayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.am.template.R
import com.am.template.data.local.models.ReportModel
import com.am.template.databinding.FragmentTodayBinding
import com.am.template.presentation.adapters.ReportsRvAdapter
import com.am.template.presentation.viewmodel.SharedViewModel
import com.am.template.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment : Fragment(R.layout.fragment_today), ReportsRvAdapter.OnItemClickListener {
    private lateinit var binding: FragmentTodayBinding

    @Inject
    lateinit var reportsRvAdapter: ReportsRvAdapter
    private val viewModelShared: SharedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTodayBinding.bind(view)

        initializeRv()
        fetchDataState()
    }

    private fun initializeRv() {
        reportsRvAdapter.setListener(this@TodayFragment)
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