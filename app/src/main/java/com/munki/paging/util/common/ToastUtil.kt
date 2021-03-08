package com.munki.paging.util.common

import android.content.Context
import android.widget.Toast

/**
 * Toast Util
 * @author 나비이쁜이
 * @since 2021.03.09
 */
object ToastUtil {

    /**
     * ToastUtil
     * run -> Toast가 null인 상태에서 생성을 해주고 show를 해줘야 하므로 run을 사용해서 변수의 범위를 제한해서 사용함
     */
    private var toast: Toast? = null

    /**
     * Short Toast Message
     */
	@JvmStatic
	fun showToastAsShort(context: Context, message: String) {
        clearToast()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.run { show() }
    }

    /**
     * Long Toast Message
     */
    @JvmStatic
    fun showToastAsLong(context: Context, message: String) {
        clearToast()
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast?.run { show() }
    }

    /**
     * Toast 객체 초기화
     */
    private fun clearToast() {
        toast?.cancel()
    }
}