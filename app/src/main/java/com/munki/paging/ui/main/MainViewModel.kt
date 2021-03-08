package com.munki.paging.ui.main

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.munki.paging.data.Contents
import com.munki.paging.data.GitPagingDataSource
import com.munki.paging.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * MainViewModel
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class MainViewModel internal constructor(application: Application) : BaseViewModel<MainNavigator>(application) {

    // Flow Paging Data
    private val flowPagingData: Flow<PagingData<Contents>> = Pager(PagingConfig(pageSize = 10)) { GitPagingDataSource() }
        .flow
        .cachedIn(viewModelScope)
        .map {
            // 데이터 추가 -> 테스트용
            it.insertHeaderItem(TerminalSeparatorType.FULLY_COMPLETE, Contents(0, "header", "header", "header", "header"))
        }

    val pagingData = flowPagingData.map { it }
}