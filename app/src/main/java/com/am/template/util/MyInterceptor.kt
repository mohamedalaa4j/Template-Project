package com.template.util

import com.am.template.util.LocalUtil
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("lang", if (LocalUtil.isEnglish()) Constants.API_HEADER_LANG_EN else Constants.API_HEADER_LANG_AR)
            .build()
        return chain.proceed(request)
    }
}