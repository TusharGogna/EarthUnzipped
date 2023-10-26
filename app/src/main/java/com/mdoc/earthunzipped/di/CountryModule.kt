package com.mdoc.earthunzipped.di

import com.apollographql.apollo3.ApolloClient
import com.mdoc.earthunzipped.data.ApolloCountryClient
import com.mdoc.earthunzipped.domain.CountryClient
import com.mdoc.earthunzipped.domain.GetCountryUseCase
import com.mdoc.earthunzipped.domain.GetZippedCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun providesCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun providesCountryZippedUseCase(countryClient: CountryClient): GetZippedCountriesUseCase {
        return GetZippedCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun providesCountryUseCases(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }
}