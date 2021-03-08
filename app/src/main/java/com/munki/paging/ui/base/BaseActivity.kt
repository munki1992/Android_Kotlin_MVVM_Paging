package com.munki.paging.ui.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * (MVVM) BaseActivity
 * B = DataBinding / M = ViewModel
 * @author 나비이쁜이
 * @since 2021.03.09
 */
abstract class BaseActivity<B: ViewDataBinding?, M : BaseViewModel<*>?> : AppCompatActivity() {

    // Context
    lateinit var mContext: Context

    // 현재 View의 DataBinding을 가져옵니다.
    var mViewDataBinding: B ? = null

    // ViewModel
    private var mViewModel: M ? = null

    /************************************************************************************************************************************************/

    // int[0] - Resoucre ID | int[1] - BR
    protected abstract val layoutVariable: IntArray

    // 상속받는 현재 ViewModel을 가져옵니다.
    protected abstract val viewModel: M

    /**
     * 상속받는 현재 ui의 DataBinding을 적용합니다.
     */
    private fun performDataBinding() {
        // Activity의 ViewDatabinding을 가져옵니다.
        mViewDataBinding = DataBindingUtil.setContentView<B>(this, layoutVariable[0])

        /**
         * (true) View Model이 존재하는 경우 getViewModel()
         * (false) View Model이 존재하지 않는 경우 mViewModel
         */
        mViewModel = if (mViewModel == null) viewModel else mViewModel

        // Binding Model를 적용
        mViewDataBinding!!.setVariable(layoutVariable[1], mViewModel)

        // Binding을 즉시 실행
        mViewDataBinding!!.executePendingBindings()
    }

    /************************************************************************************************************************************************/

    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // super onCreate
        super.onCreate(savedInstanceState)

        // Context
        mContext = this

        // Databinding
        performDataBinding()
    }

    /**
     * init
     */
    protected open fun init() {}
}