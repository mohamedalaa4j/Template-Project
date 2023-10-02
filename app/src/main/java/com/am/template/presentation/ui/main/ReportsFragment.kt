package com.am.template.presentation.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.am.template.R
import com.am.template.data.local.models.ReportModel
import com.am.template.databinding.FragmentReportsBinding
import com.am.template.presentation.adapters.MyPagerAdapter
import com.am.template.presentation.adapters.ReportsRvAdapter
import com.am.template.presentation.viewmodel.SharedViewModel
import com.am.template.presentation.viewmodel.navbottom.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.template.util.singletone.MySingleton.packageName
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReportsFragment : Fragment(R.layout.fragment_reports) {

    private lateinit var binding: FragmentReportsBinding

    @Inject
    lateinit var reportsRvAdapter: ReportsRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReportsBinding.bind(view)
        setupToolbar()

        val pagerAdapter = MyPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        binding.apply {
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.today)
                    1 -> tab.text = getString(R.string.this_week)
                    2 -> tab.text = getString(R.string.this_month)
                    3 -> tab.text = getString(R.string.this_year)
                }
            }.attach()
        }

    }

    private fun setupToolbar() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}