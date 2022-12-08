package com.example.stocktradingapp.domain.model

// The Company listing class isolates the data returned from the API in the CompanyListingEntity, ensures clean architecture
class CompanyListing(
    val name: String,
    val symbol: String,
    val exchange: String,
)