package com.munki.paging.di

import com.munki.paging.ui.main.MainFragment
import com.munki.paging.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * DI Koin Module
 * @author 나비이쁜이
 * @since 2021.03.09
 */

/**
 * 액티비티 Module
 */

/**
 * Fragment Module
 */
private val FragmentModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(get()) }
    }
}

/**
 * Application에 전달한 Module List
 */
internal val modules = listOf(FragmentModule)