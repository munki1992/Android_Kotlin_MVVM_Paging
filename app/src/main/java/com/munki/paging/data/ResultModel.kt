package com.munki.paging.data

import com.google.gson.annotations.SerializedName

/**
 * GitResult
 * @author 나비이쁜이
 * @since 2021.03.09
 */
data class ResultModel(
    @SerializedName("total_count") val totalCount : Int = 0,
    @SerializedName("incomplete_results") val incompleteResults : Boolean = false,
    @SerializedName("items") val items : List<Contents>,
    val nextPage : Int? = null
)

data class Contents (
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("full_name") val fullName : String,
    @SerializedName("description") val description : String?,
    @SerializedName("html_url") val url : String
)