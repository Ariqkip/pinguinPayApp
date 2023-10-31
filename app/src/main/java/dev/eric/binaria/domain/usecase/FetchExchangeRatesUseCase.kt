package dev.eric.binaria.domain.usecase

import dev.eric.binaria.domain.InternetService
import dev.eric.binaria.domain.common.Response
import dev.eric.binaria.domain.common.exception.InternetException
import dev.eric.binaria.domain.common.usecase.UseCaseNoInput
import dev.eric.binaria.domain.model.ExchangeRatesResponse
import dev.eric.binaria.domain.repository.ExchangeRatesRepository

class FetchExchangeRatesUseCase(
    private val internetService: InternetService,
    private val exchangeRatesRepository: ExchangeRatesRepository
): UseCaseNoInput<ExchangeRatesResponse> {
    override suspend fun execute(): Response<ExchangeRatesResponse> {
        if (!internetService.isConnected()) {
            return Response.failure(InternetException)
            throw error("not internet connection")

        }
        return exchangeRatesRepository.fetchLatestExchangeRates()
    }
}