package com.azlanjamal.search_data.mapper

import com.azlanjamal.search_data.remote.dto.BusinessDto
import com.azlanjamal.search_domain.model.Business

fun BusinessDto.toBusiness(): Business? {
    return Business(
        name = name ?: return null,
        imageUrl = image_url,
        phone = phone,
        id = id,
    )
}