package com.example.stocktradingapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings()

    // Search checks if the query text is contained within any company name OR the text matches a company symbol
    @Query("""
        SELECT *
        FROM companylistingentity
        WHERE LOWER(name) LIKE "%" || LOWER(:query) || '%' OR
        UPPER(:query) == symbol
        
    """)
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}