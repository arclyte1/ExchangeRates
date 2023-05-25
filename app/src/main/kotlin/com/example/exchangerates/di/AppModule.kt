package com.example.exchangerates.di

import com.example.exchangerates.common.Constants
import com.example.exchangerates.data.remote.ExchangeRatesApi
import com.example.exchangerates.data.repository.ExchangeRatesRepositoryImpl
import com.example.exchangerates.domain.repository.ExchangeRatesRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(Constants.EXCHANGE_RATES_BASE_URL)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRatesApi(retrofit: Retrofit): ExchangeRatesApi {
        return retrofit.create(ExchangeRatesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRatesRepository(api: ExchangeRatesApi): ExchangeRatesRepository {
        return ExchangeRatesRepositoryImpl(api)
    }
}