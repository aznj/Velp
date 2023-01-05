package com.azlanjamal.detail_domain.use_case

import com.azlanjamal.common.Resource
import com.azlanjamal.detail_domain.model.Detail
import com.azlanjamal.detail_domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetBusinessById(
    private val repository: DetailRepository
) {

    operator fun invoke(
        businessId: String
    ): Flow<Resource<Detail>> = flow {
        try {
            emit(Resource.Loading<Detail>())
            val detail = repository.getBusinessById(businessId)
            emit(Resource.Success<Detail>(detail))
        } catch (e: IOException) {
            emit(Resource.Error<Detail>("Couldn't reach server. Check your internet connection."))
        }
    }
}