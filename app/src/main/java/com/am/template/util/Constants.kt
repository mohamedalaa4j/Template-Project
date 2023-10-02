package com.template.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.am.template.R

object Constants {
    const val TOPIC = "/topics/MyTopic"

    const val TEXT_YES = "yes"
    const val TEXT_NO = "no"

    const val ANIMATION_DELAY = 450L
    const val TAG = "Events-Log"

    const val PAGING_START_INDEX = 1
    const val PAGING_PER_PAGE = 4

    const val ERAMO_WEBSITE = "https://www.e-ramo.net"
    const val ERAMO_PHONE = "tel:+201011559674"


    const val API_HEADER_LANG_EN = "en"
    const val API_HEADER_LANG_AR = "ar"

    const val API_SUCCESS_CODE = 1
    const val API_FAILURE_CODE = 0

    const val TEST_AUTH_TOKEN = "Bearer 4|CbSknQBlpztcxVhrEkFdKTBLzQ2iMuC8p0hNNYwK"

    const val LATEST_WEDDINGS_DESTINATION_CODE = 101
    const val LATEST_BIRTHDAYS_DESTINATION_CODE = 102
    const val LATEST_ENGAGEMENTS_DESTINATION_CODE = 103
    const val LATEST_CONFERENCES_DESTINATION_CODE = 104
    const val PRIVATE_DESTINATION_CODE = 109

    const val HALLS_BY_CATEGORY_ID_DESTINATION_CODE = 105

    const val SHOP_BY_BRAND_DESTINATION_CODE = 106
    const val SHOP_BY_OCCASION_DESTINATION_CODE = 107
    const val EXPLORE_CATEGORIES_DESTINATION_CODE = 108

    const val PRODUCT_SEARCH_DESTINATION_CODE = 110

    const val FORGET_PASSWORD_DESTINATION_CODE = 112
    const val REGISTRATION_DESTINATION_CODE = 113


    const val CART_PRODUCTS_SELECTED = 120
    const val CART_HALLS_SELECTED = 121


    const val BY_PRICE_HIGH = "high"
    const val BY_PRICE_LOW = "low"

    const val BY_DATE_NEWER = "newer"
    const val BY_DATE_OLDER = "older"


    const val CART_PRODUCTS_DESTINATION_CODE = 301
    const val CART_HALLS_DESTINATION_CODE = 302

    const val AVAILABLE_CODE = "1"
    const val UNAVAILABLE_CODE = "0"

    const val IS_FAVOURITE_CODE = "1"
    const val IS_NOT_FAVOURITE_CODE = "0"

    const val UNSUSPEND_CODE = "1"
    const val SUSPEND_CODE = "0"

    const val SEEN_CODE = "1"
    const val NOT_SEEN_CODE = "0"

    //___REGISTER___//
    const val IMG_KEY_PROFILE = "image"
    const val SIGN_FROM = "Android"

    const val MALE = "Android"
    const val FEMALE = "Android"

    const val STORE_ID = "28454"

    const val NOTIFICATIONS_COUNT_SHARED_PREFERENCES_KEY = "notification_count_shared_preferences_key"


    const val TELR_TOTAL_PRICE_KEY = "telrTotalPriceKey"
    const val TELR_DESCRIPTION_KEY = "telrDescriptionKey"

    const val TELR_DESCRIPTION_PRODUCTS_ORDER = "(ANDROID) Products Order"
    const val TELR_DESCRIPTION_HALL_ORDER = "(ANDROID) Hall Order"

    fun htmlFormatToString(htmlTxt: String): CharSequence {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(htmlTxt, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(htmlTxt)
    }

    fun createSpinnerAdapter(context: Context, list: List<StringWithTag>) =
        ArrayAdapter(context, R.layout.layout_spinner_item, list)


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
            if (isLangOpened) closeChooser(body, iconArrow) else openChooser(body, iconArrow)
            isLangOpened = !isLangOpened
        }

        linChoiceEn.setOnClickListener {
            closeChooser(body, iconArrow)
            LocalUtil.setLocal(activity, "en")
            ActivityCompat.recreate(activity)
        }

        linChoiceAr.setOnClickListener {
            closeChooser(body, iconArrow)
            LocalUtil.setLocal(activity, "ar")
            ActivityCompat.recreate(activity)
        }
    }

    private fun closeChooser(body: CardView, iconArrow: ImageView) {
        body.visibility = View.GONE
        iconArrow.setImageResource(R.drawable.ic_arrow_down)
    }

    private fun openChooser(body: CardView, iconArrow: ImageView) {
        body.visibility = View.VISIBLE
        iconArrow.setImageResource(R.drawable.ic_arrow_up)
    }
}