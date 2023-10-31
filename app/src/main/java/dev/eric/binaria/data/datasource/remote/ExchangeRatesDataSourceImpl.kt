package dev.eric.binaria.data.datasource.remote

import dev.eric.binaria.data.datasource.ExchangeRatesDataSource
import dev.eric.binaria.data.datasource.ExchangeRatesResponseData
import dev.eric.binaria.data.datasource.remote.error.ExchangeRatesErrorResponseData
import dev.eric.binaria.network.StockWaveHttpClient
import dev.eric.binaria.network.Constants
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess

class ExchangeRatesDataSourceImpl(
    private val httpClient: StockWaveHttpClient
): ExchangeRatesDataSource {

    override suspend fun fetchLatestExchangeRates(): ExchangeRatesResponseData {
        val response = httpClient.getHttpClient(appKey = Constants.APP_ID, symbols = Constants.SYMBOLS).get("/api/latest.json")
        if (response.status.isSuccess()){
            return response.body()
        }
        val error = response.body<ExchangeRatesErrorResponseData>()
        throw Exception(error.message)
    }
}