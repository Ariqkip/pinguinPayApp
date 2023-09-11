package dev.eric.binaria.data.datasource

import dev.eric.binaria.data.datasource.remote.model.ExchangeRatesResponseData

interface ExchangeRatesDataSource {
    suspend fun fetchLatestExchangeRates(): ExchangeRatesResponseData
}