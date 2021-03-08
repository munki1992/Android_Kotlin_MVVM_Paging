package com.munki.paging.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseViewHolder
 * @author 나비이쁜이
 * @since 2021.03.09
 */
abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * 생성자
     */
    init { setIsRecyclable(false) }

    /**
     * Item Bind
     */
    abstract fun bind(itemVo: T, position: Int?)
}