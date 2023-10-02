package com.am.template.data.repository

import com.am.template.data.remote.EventsApi
import com.am.template.domain.repository.HomeRepository

class HomeRepositoryImpl(private val EventsApi: EventsApi) : HomeRepository {
//    override suspend fun homeApis(): Flow<Resource<HomeApisResponse>> {
//        return flow {
//            val result = toResultFlow { EventsApi.homeApis() }
//            result.collect { apiState ->
//                when (apiState) {
//                    is ApiState.Loading -> emit(Resource.Loading())
//                    is ApiState.Error -> emit(Resource.Error(apiState.message!!))
//                    is ApiState.Success -> emit(Resource.Success(apiState.data))
//                }
//            }
//        }
//    }
}