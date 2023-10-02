package com.am.template.domain.repository

import com.am.template.data.local.models.ReportModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getListData(): Flow<List<ReportModel>>
}