package dev.eric.binaria.di

import dev.eric.binaria.data.datasource.ExchangeRatesDataSource
import dev.eric.binaria.data.datasource.remote.ExchangeRatesDataSourceImpl
import dev.eric.binaria.data.repository.ExchangeRatesRepositoryImpl
import dev.eric.binaria.domain.InternetService
import dev.eric.binaria.domain.InternetServiceImpl
import dev.eric.binaria.domain.repository.ExchangeRatesRepository
import dev.eric.binaria.domain.usecase.FetchExchangeRatesUseCase
import dev.eric.binaria.network.StockWaveHttpClient
import dev.eric.binaria.network.StockWaveHttpClientImpl
import dev.eric.binaria.viewmodel.ExchangeRatesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DomainModule = module {
    factory { FetchExchangeRatesUseCase(get(), get()) }
}

val DataModule = module {
    factory<StockWaveHttpClient> { StockWaveHttpClientImpl() }
    single<InternetService> { InternetServiceImpl(androidContext()) }
    single<ExchangeRatesRepository> { ExchangeRatesRepositoryImpl(get()) }
    single<ExchangeRatesDataSource>{ ExchangeRatesDataSourceImpl(get()) }
}

val ViewModelModule = module {
    viewModel { ExchangeRatesViewModel(get()) }
}