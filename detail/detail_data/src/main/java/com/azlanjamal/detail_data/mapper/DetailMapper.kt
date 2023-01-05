package com.azlanjamal.detail_data.mapper

import com.azlanjamal.detail_data.remote.dto.DetailDto
import com.azlanjamal.detail_domain.model.Coordinates
import com.azlanjamal.detail_domain.model.Detail
import com.azlanjamal.detail_domain.model.Location

fun DetailDto.toDetail(): Detail {
    return Detail(
        name = name,
        imageUrl = image_url,
        coordinates = Coordinates(latitude = coordinates.latitude, longitude = coordinates.longitude),
        isClaimed = is_claimed,
        phone = phone,
        location = Location(
            address1 = location.address1,
            address2 = location.address2,
            address3 = location.address3,
            city = location.city,
            country = location.country,
            crossStreets = location.cross_streets,
            displayAddress = location.display_address,
            state = location.state,
            zipCode = location.zip_code
        ),
        photos = photos,
    )
}