package com.am.template.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.am.template.R
import com.am.template.databinding.ActivityMainBinding
import com.am.template.presentation.ui.dialog.LoadingDialog
import com.am.template.presentation.ui.dialog.WarningDialog
import com.am.template.presentation.viewmodel.SharedViewModel
import com.am.template.util.deeplink.DeeplinkHandlerImpl
import com.am.template.util.LocalUtil
import com.template.util.StatusBarUtil
import com.template.util.deeplink.DeeplinkHandler
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DeeplinkHandler by DeeplinkHandlerImpl() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModelShared: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.init(window)
        LoadingDialog.init(this)
        WarningDialog.init(this)
        LocalUtil.init(this)
        LocalUtil.loadLocal(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup navStart
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_navHost) as NavHostFragment
        navController = navHostFragment.findNavController()
        onNewIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeeplink(intent, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}