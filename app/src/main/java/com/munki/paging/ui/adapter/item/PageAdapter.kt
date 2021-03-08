package com.munki.paging.ui.adapter.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.munki.paging.R
import com.munki.paging.data.Contents
import com.munki.paging.databinding.ItemMovieBinding
import com.munki.paging.ui.base.BaseViewHolder

/**
 * PageAdapter
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class PageAdapter : PagingDataAdapter<Contents, PageAdapter.PageViewHolder>(PageDiff) {

    /**
     * onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    /**
     * onBindViewHolder
     */
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, position)
    }

    /************************************************************************************************************************************************/

    /**
     * ViewHolder
     */
    inner class PageViewHolder (view: View) : BaseViewHolder<Contents>(view) {

        /**
         * Item View DataBinding
         */
        private var mBinding : ItemMovieBinding? = DataBindingUtil.bind(view)

        /**
         * Bind
         */
        override fun bind(itemVo: Contents, position: Int?) {
            itemVo.let {
                mBinding?.data = it
            }
        }
    }
}

