package com.munki.paging.util.common

import android.util.Log
import com.munki.paging.BuildConfig

/**
 * Log Util
 * @author 나비이쁜이
 * @since 2021.03.09
 */
object LogUtil {

    /**
     * Timeout
     */
    private const val SETTING_1 = "["
    private const val SETTING_2 = " > "
    private const val SETTING_3 = "()"
    private const val SETTING_4 = " > #"
    private const val SETTING_5 = "] "

    /**
     * verbose
     */
    @JvmStatic
    fun v(logString: String) {
        if (BuildConfig.DEBUG)
            Log.v(buildTagName(), logString)
    }

    /**
     * debug
     */
    @JvmStatic
    fun d(logString: String) {
        if (BuildConfig.DEBUG)
            Log.d(buildTagName(), logString)
    }

    /**
     * info
     */
    @JvmStatic
    fun i(logString: String) {
        if (BuildConfig.DEBUG)
            Log.i(buildTagName(), logString)
    }

    /**
     * warn
     */
    @JvmStatic
    fun w(logString: String) {
        if (BuildConfig.DEBUG)
            Log.w(buildTagName(), logString)
    }

    /**
     * error
     */
    @JvmStatic
    fun e(logString: String) {
        if (BuildConfig.DEBUG)
            Log.e(buildTagName(), logString)
    }

    /**
     * Log TAG String 생성
     * 파일명, 메소드명, 라인번호 정보를 표시함.
     */
    private fun buildTagName(): String {
        val ste = Throwable().stackTrace[2]
        return SETTING_1 + ste.fileName +
                SETTING_2 + ste.methodName + SETTING_3 +
                SETTING_4 + ste.lineNumber + SETTING_5
    }
}