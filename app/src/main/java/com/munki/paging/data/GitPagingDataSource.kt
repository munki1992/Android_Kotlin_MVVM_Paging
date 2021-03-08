package com.munki.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * GitPagingDataSource
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class GitPagingDataSource : PagingSource<Int, Contents>() {

    // suspend Git Api
    private suspend fun requestGitApi(page: Int, perPage: Int): ResultModel =
        GitApi.create().requestGit("android", page, perPage)

    /**
     * load
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contents> {
        return try {
            val nextPageNumber = params.key ?: 1
            val perPage = params.loadSize
            val response = requestGitApi(nextPageNumber, perPage)

            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e : Exception){
            LoadResult.Error(e)
        }
    }

    /**
     * getRefreshKey
     */
    override fun getRefreshKey(state: PagingState<Int, Contents>): Int? {
        return state.anchorPosition
    }
}