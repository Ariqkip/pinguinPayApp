package dev.eric.binaria.data.repository

import dev.eric.binaria.data.datasource.ExchangeRatesDataSource
import dev.eric.binaria.data.datasource.toDomain
import dev.eric.binaria.domain.common.Response
import dev.eric.binaria.domain.model.ExchangeRatesResponse
import dev.eric.binaria.domain.repository.ExchangeRatesRepository

class ExchangeRatesRepositoryImpl(
    private val dataSource: ExchangeRatesDataSource
): ExchangeRatesRepository {
    override suspend fun fetchLatestExchangeRates(): Response<ExchangeRatesResponse> {
        return try {
            Response.success(dataSource.fetchLatestExchangeRates().toDomain())
        }catch (e: Exception){
            Response.failure(e)
        }
    }
}