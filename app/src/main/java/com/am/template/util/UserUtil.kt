package com.template.util

import android.content.Context
import android.content.SharedPreferences
import java.util.regex.Pattern

object UserUtil {

    private lateinit var sharedPreferences: SharedPreferences

    private const val IS_FIRST_TIME = "isFirsTime"
    private const val REMEMBER = "remember"

    private const val USER_ID = "id"
    private const val FIRST_NAME = "fname"
    private const val LAST_NAME = "lname"
    private const val EMAIL = "email"
    private const val PHONE = "phone"

    private const val VERIFIED_STATUS = "verified_status"
    private const val STATUS = "status"

    private const val IMAGE = "image"
    private const val LANG = "lang"

    private const val ADDRESS = "address"
    private const val LAT = "lat"
    private const val LONG = "long"

    private const val CITY_ID = "city_id"
    private const val REGION_ID = "region_id"
    private const val CITY_NAME = "city_name"
    private const val REGION_NAME = "region_name"

    private const val FILTER_BY_CITY_ID = "filter_by_city_id"
    private const val FILTER_BY_CITY_NAME = "filter_by_city_name"

    private const val USER_TOKEN = "token"

    private const val USER_PASS = "userPass"
    private const val HAS_DEEP_LINK = "HAS_DEEP_LINK"

//    private const val COUNTRY_ID = "countryId"
//    private const val USER_PROFILE = "user_profile"

    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{6,}" +  //at least 4 characters
                "$"
    )

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_util", Context.MODE_PRIVATE)
    }

    fun saveUserInfo(
        isRemember: Boolean,
        password: String?,
        id: Int,
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        verifiedStatus: Boolean,
        status: Boolean,
        image: String,
        lang: String,
        address: String,
        lat: String,
        long: String,
        cityId: String,
        regionId: String,
        cityName: String,
        regionName: String,
        token: String,
    ) {
        sharedPreferences.edit().putBoolean(REMEMBER, isRemember).apply()

        sharedPreferences.edit().putInt(USER_ID, id).apply()
        sharedPreferences.edit().putString(FIRST_NAME, firstName).apply()
        sharedPreferences.edit().putString(LAST_NAME, lastName).apply()

        sharedPreferences.edit().putString(EMAIL, email).apply()
        sharedPreferences.edit().putString(PHONE, phone).apply()

        sharedPreferences.edit().putBoolean(VERIFIED_STATUS, verifiedStatus).apply()
        sharedPreferences.edit().putBoolean(STATUS, status).apply()

        sharedPreferences.edit().putString(IMAGE, image).apply()
        sharedPreferences.edit().putString(LANG, lang).apply()

        sharedPreferences.edit().putString(ADDRESS, address).apply()
        sharedPreferences.edit().putString(LAT, lat).apply()
        sharedPreferences.edit().putString(LONG, long).apply()

        sharedPreferences.edit().putString(CITY_ID, cityId).apply()
        sharedPreferences.edit().putString(REGION_ID, regionId).apply()
        sharedPreferences.edit().putString(CITY_NAME, cityName).apply()
        sharedPreferences.edit().putString(REGION_NAME, regionName).apply()

        sharedPreferences.edit().putString(USER_TOKEN, token).apply()
        sharedPreferences.edit().putString(USER_PASS, password).apply()
    }

    fun saveUserProfile(image: String) {
        sharedPreferences.edit().putString(IMAGE, image).apply()
    }

    // ---------------------------- FILTER BY CITY ----------------------------//

    fun saveFilterByCityId(cityId: String) {
        sharedPreferences.edit().putString(FILTER_BY_CITY_ID, cityId).apply()
    }

    fun getFilterByCityId() = sharedPreferences.getString(FILTER_BY_CITY_ID, "") ?: ""

    fun saveFilterByCityName(cityName: String) {
        sharedPreferences.edit().putString(FILTER_BY_CITY_NAME, cityName).apply()
    }

    fun getFilterByCityName() = sharedPreferences.getString(FILTER_BY_CITY_NAME, "") ?: ""

    // ---------------------------- FILTER BY CITY ----------------------------//


    fun clearUserInfo() {
//        sharedPreferences.edit().clear().apply()
        sharedPreferences.edit()
            .remove(REMEMBER)
            .remove(USER_ID)
            .remove(FIRST_NAME)
            .remove(LAST_NAME)
            .remove(EMAIL)
            .remove(PHONE)
            .remove(VERIFIED_STATUS)
            .remove(STATUS)
            .remove(IMAGE)
            .remove(ADDRESS)
            .remove(LAT)
            .remove(LONG)
            .remove(CITY_ID)
            .remove(REGION_ID)
            .remove(CITY_NAME)
            .remove(REGION_NAME)
            .remove(USER_TOKEN)
            .remove(FILTER_BY_CITY_ID)
            .remove(FILTER_BY_CITY_NAME)
            .remove(USER_PASS).apply()
//        sharedPreferences.edit().putBoolean(REMEMBER, false).apply()
//        sharedPreferences.edit().putString(USER_ID, "").apply()
//        sharedPreferences.edit().putString(USER_TOKEN, "").apply()
//        sharedPreferences.edit().putString(USERNAME, "").apply()
//        sharedPreferences.edit().putString(USER_PASS, "").apply()
//        sharedPreferences.edit().putString(ADDRESS, "").apply()
//        sharedPreferences.edit().putString(USER_PHONE, "").apply()
//        sharedPreferences.edit().putString(USER_EMAIL, "").apply()
//        sharedPreferences.edit().putString(USER_PROFILE, "").apply()
    }

    fun saveFirstTime() = sharedPreferences.edit().putBoolean(IS_FIRST_TIME, false).apply()

    fun isFirstTime() = sharedPreferences.getBoolean(IS_FIRST_TIME, true)

    //    fun isUserLogin() = getUserId().isNotEmpty()
    fun isUserLogin() = getUserId() != 0

    fun isRememberUser() = sharedPreferences.getBoolean(REMEMBER, false)

    //    fun getUserId() = sharedPreferences.getString(USER_ID, "") ?: ""
    fun getUserId() = sharedPreferences.getInt(USER_ID, 0) ?: ""

    fun getUserToken() = sharedPreferences.getString(USER_TOKEN, "") ?: ""

    fun getUserFirstName() = sharedPreferences.getString(FIRST_NAME, "") ?: ""
    fun getUserLastName() = sharedPreferences.getString(LAST_NAME, "") ?: ""

    fun getUserPass() = sharedPreferences.getString(USER_PASS, "") ?: ""

    fun getUserAddress() = sharedPreferences.getString(ADDRESS, "") ?: ""

//    fun getCountryId() = sharedPreferences.getString(COUNTRY_ID, "") ?: ""

    fun getCityId() = sharedPreferences.getString(CITY_ID, "") ?: ""
    fun getCityName() = sharedPreferences.getString(CITY_NAME, "") ?: ""

    fun getRegionId() = sharedPreferences.getString(REGION_ID, "") ?: ""
    fun getRegionName() = sharedPreferences.getString(REGION_NAME, "") ?: ""

    fun getUserPhone() = sharedPreferences.getString(PHONE, "") ?: ""

    fun getUserEmail() = sharedPreferences.getString(EMAIL, "") ?: ""

    fun getUserProfileImage() = sharedPreferences.getString(IMAGE, "") ?: ""

    fun setHasDeepLink(hasDeepLink: Boolean) = sharedPreferences.edit().putBoolean(HAS_DEEP_LINK, hasDeepLink).apply()

    fun hasDeepLink() = sharedPreferences.getBoolean(HAS_DEEP_LINK, false)

}