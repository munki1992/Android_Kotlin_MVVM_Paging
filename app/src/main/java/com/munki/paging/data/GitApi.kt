package com.munki.paging.data

import com.munki.paging.util.network.RetroUtil
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * GitApi
 * @author 나비이쁜이
 * @since 2021.03.09
 */
interface GitApi {

    companion object {
        fun create(): GitApi {
            return RetroUtil.createService("https://api.github.com/", GitApi::class.java)
        }
    }

    @GET("search/repositories?sort=stars")
    suspend fun requestGit(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): ResultModel
}