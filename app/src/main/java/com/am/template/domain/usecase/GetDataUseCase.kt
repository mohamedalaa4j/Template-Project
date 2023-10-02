package com.am.template.domain.usecase

import com.am.template.data.local.models.ReportModel
import com.am.template.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetDataUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(
    ): Flow<List<ReportModel>> {
        return repository.getListData()
    }
}