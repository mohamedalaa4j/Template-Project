package com.am.template.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.am.template.R
import com.am.template.databinding.FragmentHomeBinding
import com.am.template.util.navOptionsAnimation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.homeBtn.setOnClickListener {
            findNavController().navigate(R.id.reportsFragment, null, navOptionsAnimation())
        }
    }
}