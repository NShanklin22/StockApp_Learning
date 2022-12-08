package com.example.stocktradingapp.data.mapper

import com.example.stocktradingapp.data.local.CompanyListingEntity
import com.example.stocktradingapp.domain.model.CompanyListing

// This is what transforms the company listing entity into a company listing class
// This "maps" the entity to the class

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}
