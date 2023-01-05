package com.azlanjamal.detail_domain.repository

import com.azlanjamal.detail_domain.model.Detail

interface DetailRepository {

    suspend fun getBusinessById(
        businessId: String
    ): Detail
}