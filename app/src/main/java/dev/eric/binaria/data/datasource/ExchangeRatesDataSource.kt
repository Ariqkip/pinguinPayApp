package dev.eric.binaria.data.datasource

interface ExchangeRatesDataSource {
    suspend fun fetchLatestExchangeRates(): ExchangeRatesResponseData
}