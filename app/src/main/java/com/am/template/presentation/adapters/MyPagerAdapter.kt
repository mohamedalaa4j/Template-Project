package com.am.template.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.am.template.presentation.ui.main.tablayout.MonthFragment
import com.am.template.presentation.ui.main.tablayout.TodayFragment
import com.am.template.presentation.ui.main.tablayout.WeekFragment
import com.am.template.presentation.ui.main.tablayout.YearFragment


class MyPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> TodayFragment()
            2 -> WeekFragment()
            3 -> MonthFragment()
            else -> YearFragment()
        }
    }
}