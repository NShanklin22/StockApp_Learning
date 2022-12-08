package com.example.stocktradingapp.presentation.company_listings

import androidx.room.Query
import com.example.stocktradingapp.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)