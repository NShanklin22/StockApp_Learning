package com.example.stocktradingapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
// The entity annotation basically says that this data class will be a table in the database
data class CompanyListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    // Primary key tells room that this is the unique key for the table entries and room will automatically generate the unique identifiers
    @PrimaryKey val id: Int? = null
)
