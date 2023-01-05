package com.azlanjamal.detail_data.repository

import com.azlanjamal.detail_data.mapper.toDetail
import com.azlanjamal.detail_data.remote.DetailApi
import com.azlanjamal.detail_domain.model.Detail
import com.azlanjamal.detail_domain.repository.DetailRepository

class DetailRepositoryImpl(
    private val api: DetailApi
): DetailRepository {

    override suspend fun getBusinessById(
        businessId: String
    ): Detail {
        val detailDto = api.getBusinessById(
            businessId = businessId
        )
        return detailDto.toDetail()
    }
}