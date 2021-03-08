package com.munki.paging.ui.adapter.footer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.munki.paging.R
import com.munki.paging.databinding.ItemFooterBinding
import com.munki.paging.ui.base.BaseViewHolder

/**
 * FooterAdapter
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class FooterAdapter : LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    /**
     * onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        return FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false))
    }

    /**
     * onBindViewHolder
     */
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState, null)
    }

    /************************************************************************************************************************************************/

    /**
     * ViewHolder
     */
    inner class FooterViewHolder (view: View) : BaseViewHolder<LoadState>(view) {

        /**
         * Item View DataBinding
         */
        private var mBinding : ItemFooterBinding? = DataBindingUtil.bind(view)

        /**
         * Bind
         */
        override fun bind(itemVo: LoadState, position: Int?) {
            /* ProgressBar가 재사용되도록 설정 */
            setIsRecyclable(true)

            // loading 상태의 경우에만 설정
            mBinding?.progressBar!!.isVisible = itemVo is LoadState.Loading
        }
    }
}

