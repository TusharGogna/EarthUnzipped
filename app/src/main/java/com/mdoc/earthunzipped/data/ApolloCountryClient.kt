package com.mdoc.earthunzipped.data

import com.apollographql.apollo3.ApolloClient
import com.mdoc.CountriesQuery
import com.mdoc.CountryQuery
import com.mdoc.earthunzipped.domain.Country
import com.mdoc.earthunzipped.domain.CountryClient
import com.mdoc.earthunzipped.domain.CountryZipped

class ApolloCountryClient(private val apolloClient: ApolloClient) : CountryClient {

    override suspend fun getCountries(): List<CountryZipped> {
        return apolloClient.query(CountriesQuery())
            .execute().data?.countries?.map { it.toZippedCountries() } ?: emptyList()
    }

    override suspend fun getSingleCountry(code: String): Country? {
        return apolloClient.query(CountryQuery(code)).execute().data?.country?.toReadableCountry()
    }
}