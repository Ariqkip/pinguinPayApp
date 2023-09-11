package dev.eric.binaria.domain.repository

import dev.eric.binaria.domain.common.Response
import dev.eric.binaria.domain.model.ExchangeRatesResponse

interface ExchangeRatesRepository {
    suspend fun fetchLatestExchangeRates(): Response<ExchangeRatesResponse>
}