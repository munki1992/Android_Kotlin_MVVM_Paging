package com.munki.paging.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

/**
 * Base ViewModel
 * @author 나비이쁜이
 * @since 2021.03.09
 */
abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    /**
     * Interfaces navigator
     */
    private var navigator: WeakReference<N>? = null

    /**
     * get
     */
    val navigation: N? get() = navigator!!.get()

    /**
     * set
     */
    fun setNavigation(navigator: N) {
        this.navigator = WeakReference(navigator)
    }
}