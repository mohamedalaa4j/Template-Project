package com.am.template.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.am.template.R
import com.am.template.databinding.ActivityMainBinding
import com.am.template.presentation.ui.dialog.LoadingDialog
import com.am.template.presentation.ui.dialog.WarningDialog
import com.am.template.presentation.viewmodel.SharedViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.template.util.Constants.TAG
import com.template.util.Constants.TOPIC
import com.template.util.LocalUtil
import com.template.util.StatusBarUtil
import com.template.util.UserUtil
import com.template.util.deeplink.DeeplinkHandler
import com.am.template.util.deeplink.DeeplinkHandlerImpl
import com.am.template.util.hideSoftKeyboard
import com.template.util.notification.FirebaseMessageReceiver
import com.template.util.singletone.MySingleton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DeeplinkHandler by DeeplinkHandlerImpl() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModelShared: SharedViewModel by viewModels()

    private lateinit var analytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.init(window)
        UserUtil.init(this)
        LoadingDialog.init(this)
        WarningDialog.init(this)
        LocalUtil.init(this)
        LocalUtil.loadLocal(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        FirebaseApp.getInstance()
        FirebaseMessageReceiver.sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        FirebaseMessaging.getInstance().token.addOnSuccessListener { firebaseToken ->
            FirebaseMessageReceiver.token = firebaseToken
            Log.d(TAG, "onCreate: $firebaseToken")
            Log.e("firebaseToken", firebaseToken)
            MySingleton.firebaseToken = firebaseToken
        }

        // Google analytics
        analytics = Firebase.analytics

        //setup navStart
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_navHost) as NavHostFragment
        navController = navHostFragment.findNavController()
        onNewIntent(intent)

        // Icon Badger


        binding.apply {
            mainBn.background = null
            mainBn.menu.getItem(2).isEnabled = false
            mainBn.setupWithNavController(navController)
            mainFabHome.setOnClickListener {
                navController.popBackStack(R.id.homeFragment, false)
            }

            viewModelShared.openDrawer.observe(this@MainActivity) { isOpen ->
                if (isOpen) {
                    hideSoftKeyboard()
                    mainDrawerLayout.openDrawer(GravityCompat.START)
                    viewModelShared.openDrawer.value = false
                }
            }

            viewModelShared.profileData.observe(this@MainActivity) { member ->
                inDrawerHeader.apply {
                    navHeaderTvName.text = member.userName
//                    UserUtil.saveUserProfile(EventsApi.IMAGE_URL_PROFILE + member.mImage!!)
//                    Glide.with(this@MainActivity).load(EventsApi.IMAGE_URL_PROFILE + member.mImage)
//                        .into(navHeaderIvProfile)
                }
            }
        }
        setupDrawer()

        setupNavBottomVisibility()


        setupLoginLogoutDrawerLabel()

        binding.swiper.isRefreshing = false
        binding.swiper.isEnabled = false
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeeplink(intent, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupDrawer() {
        binding.inDrawerHeader.apply {
            setupLangChooser(
                this@MainActivity,
                layoutLangIvFlag,
                layoutLangCvHeader,
                layoutLangCvBody,
                layoutLangArrow,
                layoutLangIvCheckEn,
                layoutLangIvCheckAr,
                layoutLangLinChoiceEn,
                layoutLangLinChoiceAr
            )

            navHeaderIvBack.setOnClickListener {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }

            navHeaderTvMyAccount.setOnClickListener {
                if (UserUtil.isUserLogin()) {
//                    viewModelShared.cancelRequest()
//                    navController.navigate(R.id.myAccountFragment)

                } else {
//                    navController.navigate(R.id.logInDialog)

                }

                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }

        }
    }

    private fun setupBottomNotificationsItemsCount(notificationCount: Int) {
        binding.mainBn.apply {

//            if (notificationCount == 0) {
//                removeBadge(R.id.notificationFragment)
//            } else {
            getOrCreateBadge(R.id.notificationFragment).apply {
                number = notificationCount
                verticalOffset = 8
                horizontalOffset = 8
                backgroundColor =
                    ContextCompat.getColor(this@MainActivity, R.color.events_orange)

            }
        }
    }

    private fun setupBottomCartItemsCount(cartCount: Int) {
        binding.mainBn.apply {

//            if (cartCount == 0) {
//                removeBadge(R.id.cartFragment)
//            } else {
            getOrCreateBadge(R.id.cartFragment).apply {
                Log.e("cartCount", cartCount.toString())
                number = cartCount
                verticalOffset = 8
                horizontalOffset = 8
                backgroundColor =
                    ContextCompat.getColor(this@MainActivity, R.color.events_orange)

            }
//            }
        }
    }

    private fun setupNavBottomVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            when (destination.id) {

//                R.id.suspendDialog -> {
//                    binding.apply {
//                        mainBottomAppBar.visibility = View.GONE
//                        mainFabHome.visibility = View.GONE
//                    }
//                }

                R.id.homeFragment -> {
                    binding.apply {
                        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                        mainBottomAppBar.visibility = View.VISIBLE
                        mainFabHome.visibility = View.VISIBLE
                    }
                }

                else -> {
                    binding.apply {
                        mainBottomAppBar.visibility = View.VISIBLE
                        mainFabHome.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun setupLangChooser(
        activity: Activity,
        flagIcon: ImageView,
        header: CardView,
        body: CardView,
        iconArrow: ImageView,
        iconCheckEn: ImageView,
        iconCheckAr: ImageView,
        linChoiceEn: LinearLayout,
        linChoiceAr: LinearLayout,
    ) {
        var isLangOpened = false
        if (LocalUtil.isEnglish()) {
            iconCheckEn.visibility = View.VISIBLE
            iconCheckAr.visibility = View.INVISIBLE
            flagIcon.setImageResource(R.drawable.pic_en)
        } else {
            iconCheckEn.visibility = View.INVISIBLE
            iconCheckAr.visibility = View.VISIBLE
            flagIcon.setImageResource(R.drawable.pic_emirates)
        }

        header.setOnClickListener {
            if (isLangOpened) closeChooser(body, iconArrow) else openChooser(
                body,
                iconArrow
            )
            isLangOpened = !isLangOpened
        }

        linChoiceEn.setOnClickListener {
            closeChooser(body, iconArrow)
            LocalUtil.setLocal(activity, "en")
            if (UserUtil.isUserLogin()) {
//                viewModelShared.updateLang("en")
            }
            ActivityCompat.recreate(activity)
        }

        linChoiceAr.setOnClickListener {
            closeChooser(body, iconArrow)
            LocalUtil.setLocal(activity, "ar")
            if (UserUtil.isUserLogin()) {
//                viewModelShared.updateLang("ar")
            }
            ActivityCompat.recreate(activity)
        }
    }

    private fun closeChooser(body: CardView, iconArrow: ImageView) {
        body.visibility = View.GONE
        iconArrow.setImageResource(R.drawable.ic_heart)
    }

    private fun openChooser(body: CardView, iconArrow: ImageView) {
        body.visibility = View.VISIBLE
        iconArrow.setImageResource(R.drawable.ic_heart)
    }

    private fun setupLoginLogoutDrawerLabel() {
//        if (UserUtil.isUserLogin()) {
//            binding.inDrawerHeader.navHeaderTvLogout.text = getString(R.string.logout)
//        } else {
//            binding.inDrawerHeader.navHeaderTvLogout.text = getString(R.string.login_logout)
//        }
    }

}