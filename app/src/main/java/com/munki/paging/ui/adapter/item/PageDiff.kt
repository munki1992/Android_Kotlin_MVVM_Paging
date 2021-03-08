package com.munki.paging.ui.adapter.item

import androidx.recyclerview.widget.DiffUtil
import com.munki.paging.data.Contents

/**
 * PageAdapter
 * @author 나비이쁜이
 * @since 2021.03.09
 */
object PageDiff : DiffUtil.ItemCallback<Contents>() {

    /**
     * areItemsTheSame : 아이템이 서로 같은가? ID 체크가 기본
     */
    override fun areItemsTheSame(oldItem: Contents, newItem: Contents): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * areContentsTheSame : 현재 아이템과 새로운 아이템의 equals 비교
     */
    override fun areContentsTheSame(oldItem: Contents, newItem: Contents): Boolean {
        return oldItem == newItem
    }
}