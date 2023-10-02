package com.am.template.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.am.template.data.remote.EventsApi
import com.template.util.Constants
import retrofit2.HttpException
import java.io.IOException

//class PagingProducts(private val EventsApi: EventsApi) :
//    PagingSource<Int, AllProductsResponse.Data>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllProductsResponse.Data> {
//        val page = params.key ?: Constants.PAGING_START_INDEX
//        Log.e("Paging", "come here")
//
//
//        return try {
//            Log.e("Paging", "come here")
//            val result = EventsApi.getAllProducts().body()?.data  //as List<AllProductsResponse.Data>
//
//                LoadResult.Page(
//                    data = emptyList(),
//                    prevKey = if (page == Constants.PAGING_START_INDEX) null else page - 1,
//                    nextKey = if (result?.isEmpty() == true) null else page + 1
//                )
//
//
//
////                val result = EventsApi.allProductsByUserId(
////                page.toString(),
////                Constants.PAGING_PER_PAGE.toString(),
////                UserUtil.getUserId(),
////                Constants.TEXT_NO
////            ).body()!!.allProducts.map { it.toProductModel() }
////
////            LoadResult.Page(
////                data = result,
////                prevKey = if (page == Constants.PAGING_START_INDEX) null else page - 1,
////                nextKey = if (result.isEmpty()) null else page + 1
////            )
//        } catch (e: IOException) {
//            Log.e("Paging", "come here")
//            LoadResult.Error(e)
//
//        } catch (e: HttpException) {
//            Log.e("Paging", "come here")
//            LoadResult.Error(e)
//        } catch (e: Exception) {
//            Log.e("Paging", "come here")
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, AllProductsResponse.Data>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}