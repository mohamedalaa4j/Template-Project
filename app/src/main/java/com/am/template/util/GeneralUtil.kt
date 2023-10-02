package com.am.template.util

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.paging.PagingConfig
import com.am.template.R
import com.template.util.Constants
import com.template.util.Constants.TAG
import com.template.util.LocalUtil
import com.template.util.state.ApiState
import com.template.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun ImageView.setColor(color: Int) {
    this.setColorFilter(
        ContextCompat.getColor(context, color),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
}

fun NavController.isFragmentExist(destinationId: Int) =
    try {
        getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        false
    }

fun navOptionsAnimation(): NavOptions {
    return if (LocalUtil.isEnglish())
        NavOptions.Builder()
            .setEnterAnim(R.anim.from_right)
            .setExitAnim(R.anim.to_left)
            .setPopEnterAnim(R.anim.from_left)
            .setPopExitAnim(R.anim.to_right)
            .build()
    else
        NavOptions.Builder()
            .setEnterAnim(R.anim.from_left)
            .setExitAnim(R.anim.to_right)
            .setPopEnterAnim(R.anim.from_right)
            .setPopExitAnim(R.anim.to_left)
            .build()
}

fun pagingConfig() = PagingConfig(pageSize = Constants.PAGING_PER_PAGE, enablePlaceholders = false)

fun <T>     toResultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {
    emit(ApiState.Loading())
    try {
        val response = call()
        if (response.isSuccessful) {
            emit(ApiState.Success(response.body()))
        } else {
            val errorBodyJson = JSONObject(response.errorBody()!!.charStream().readText())
            Log.e("ERROR_BODY",errorBodyJson.toString())
            emit(ApiState.Error(UiText.DynamicString(errorBodyJson.toString())))
        }
    } catch (e: HttpException) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
    } catch (e: IOException) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.check_your_internet_connection)))
    } catch (e: Exception) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
    }
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onLeftDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingLeft) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}
