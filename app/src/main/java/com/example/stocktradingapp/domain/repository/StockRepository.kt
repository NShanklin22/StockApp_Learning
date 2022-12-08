package com.example.stocktradingapp.domain.repository

import com.example.stocktradingapp.domain.model.CompanyListing
import com.example.stocktradingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

}