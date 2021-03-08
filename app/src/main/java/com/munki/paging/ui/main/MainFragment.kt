package com.munki.paging.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.munki.paging.BR
import com.munki.paging.R
import com.munki.paging.databinding.FragmentMainBinding
import com.munki.paging.ui.adapter.footer.FooterAdapter
import com.munki.paging.ui.adapter.item.PageAdapter
import com.munki.paging.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.androidx.scope.lifecycleScope as KoinScope

/**
 * MainActivity
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(), MainNavigator {

    // Databinding & Viewmodel
    private lateinit var mBinding: FragmentMainBinding
    private val mViewModel: MainViewModel by KoinScope.viewModel(this)

    // Adapter
    private lateinit var adapter: PageAdapter

    // Static
    companion object {
        private const val DEFAULT_SPAN = 2
        private const val ITEM_SPAN = 1
    }

    /************************************************************************************************************************************************/

    override val layoutVariable: IntArray get() = intArrayOf(R.layout.fragment_main, BR.main)

    override val viewModel: MainViewModel get() {
        mViewModel.setNavigation(this)
        return mViewModel
    }

    /************************************************************************************************************************************************/

    /**
     * uiInit
     */
    override fun uiInit(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = mViewDataBinding!!

        // Fragment 초기화 설정
        resetPageInfo()

        // Fragment 화면에 들어올때
        if (isAdded && !isStartedRequest)
            startRequest()

        // Fragment 화면에 들어올때
        return mBinding.root
    }

    /**
     * resetPageInfo
     */
    override fun resetPageInfo() {

        /**
         * Step 1. 페이지 어댑터 생성
         */
        adapter = PageAdapter()

        /**
         * Step 2. Swipe Refresh 생성
         */
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        /**
         * Step 3. load 상태값에 따른 화면 정의 (Visible)
         */
        adapter.addLoadStateListener {
            val isRefreshing = it.refresh is LoadState.Loading

            // 로딩 상태의 경우
            mBinding.rvGit.isInvisible = isRefreshing
            mBinding.progressBar.isVisible = isRefreshing
        }

        /**
         * Step 4. Footer Adapter 생성 및 적용
         */
        val adapterWithFooter = adapter.withLoadStateFooter(footer = FooterAdapter())

        /**
         * Step 5. 아이템 위치에 맞춰서 고정 (LayoutManager)
         */
        val gridLayoutManager = GridLayoutManager(mContext, DEFAULT_SPAN)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val itemViewType = adapterWithFooter.getItemViewType(position)
                val isItem = itemViewType == 0
                return if (isItem) ITEM_SPAN else DEFAULT_SPAN
            }
        }

        /**
         * Step 6. Recyclerview에 상위 스텝에서 작업한 결과들을 적용
         */
        with(mBinding.rvGit) {
            this.layoutManager = gridLayoutManager
            this.adapter = adapterWithFooter
        }
    }

    /**
     * startRequest
     */
    override fun startRequest() {
        // 앱이 실행되는 경우 작업 진행
        lifecycleScope.launchWhenCreated {
            mViewModel.pagingData.collect {
                mBinding.swipeRefreshLayout.isRefreshing = false
                adapter.submitData(lifecycle, it)
            }
        }
    }
}

