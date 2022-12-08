package com.example.stocktradingapp.data.repository

import com.example.stocktradingapp.data.csv.CSVParser
import com.example.stocktradingapp.data.csv.CompanyListingsParser
import com.example.stocktradingapp.data.local.StockDatabase
import com.example.stocktradingapp.data.mapper.toCompanyListing
import com.example.stocktradingapp.data.mapper.toCompanyListingEntity
import com.example.stocktradingapp.data.remote.StockApi
import com.example.stocktradingapp.domain.model.CompanyListing
import com.example.stocktradingapp.domain.repository.StockRepository
import com.example.stocktradingapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CSVParser<CompanyListing>
): StockRepository{

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow{
            emit(Resource.Loading())
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing()}
            ))

            // Check that the local listings is blank AND the query is blank because if query is "hwhwhfh" the local listings will be blank but not the db therefore no API call required
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromDatabase = !isDbEmpty && !fetchFromRemote

            if(shouldJustLoadFromDatabase){
                emit(Resource.Loading(false))
                return@flow
            }

            // Use a try-catch statement for API call to handle inevitable errors
            val remoteListings = try{
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let {listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map {it.toCompanyListingEntity()}
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map{it.toCompanyListing()}
                ))
                // Removing this line for now because it is giving an unkown error
                // emit(Resource.Loading(isLoading = false))
            }
        }
    }
}