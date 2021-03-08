package com.munki.paging.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * BaseFragment
 * B = DataBinding / M = ViewModel
 * @author 나비이쁜이
 * @since 2021.03.09
 */
abstract class BaseFragment<B : ViewDataBinding?, M> : Fragment() {

    // Context
    lateinit var mContext: Context

    // Fragment 진입 후 데이터 요청 여부
    protected var isStartedRequest = false

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
     * Fragment가 화면에 붙은 경우
     * 생명주기상으로 onAttach -> onCreate -> onCreateView
     */
    override fun onAttach(context: Context) {
        // super onAttach
        super.onAttach(context)

        // Context
        this.mContext = context
    }

    /**
     * uiInit 메소드를 호출함으로써 하위 Fragment에서는 uiInit 메소드만 Override하여 사용한다
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Databinding
        performDataBinding(inflater, container)

        // return
        return uiInit(inflater, container, savedInstanceState)
    }

    /**
     * Fragment uiInit
     */
    protected abstract fun uiInit(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    /**
     * 게시물 리스트 정보 초기화
     */
    protected abstract fun resetPageInfo()

    /**
     * 게시글 데이터 요청
     */
    protected abstract fun startRequest()

    /**
     * 상속받는 현재 ui의 DataBinding을 적용합니다.
     */
    private fun performDataBinding(inflater: LayoutInflater, parent: ViewGroup?) {
        // Activity의 ViewDatabinding을 가져옵니다.
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutVariable[0], parent, false)

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
}