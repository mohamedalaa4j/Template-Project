package com.am.template.domain.usecase

import com.am.template.domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeApisUseCase @Inject constructor(private val repository: HomeRepository) {

}